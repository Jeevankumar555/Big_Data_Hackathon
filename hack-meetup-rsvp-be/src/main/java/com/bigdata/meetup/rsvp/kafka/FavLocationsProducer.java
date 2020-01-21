package com.bigdata.meetup.rsvp.kafka;

import com.bigdata.meetup.rsvp.json.RSVP;
import com.bigdata.meetup.rsvp.source.RsvpStreamingSource;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class FavLocationsProducer {

    @Value("${kafka.bootstrapserver}")
    private String bootstrapServer;

    @Value("${rsvp.url}")
    private String rsvpUrl;

    @Value("${kafka.topic.fav.loc}")
    private String topic;

    @Value("${kafka.request.timeout.ms}")
    private String requestTimeout;

    @EventListener(ContextRefreshedEvent.class)
    @Async
    public void produce() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        DataStream<RSVP> rsvps = env.addSource(new RsvpStreamingSource(rsvpUrl));
        rsvps
                .filter(new FilterFunction<RSVP>() {
                    @Override
                    public boolean filter(RSVP rsvp) throws Exception {
                        return rsvp.getGroup() != null &&
                                rsvp.getGroup().getGroup_city() != null &&
                                rsvp.getVenue() != null &&
                                rsvp.getVenue().getLat() != null &&
                                rsvp.getVenue().getLon() != null &&
                                !rsvp.getVenue().getLat().isEmpty() &&
                                !rsvp.getVenue().getLon().isEmpty() &&
                                !("0".equalsIgnoreCase(rsvp.getVenue().getLat()) &&
                                        "0".equalsIgnoreCase(rsvp.getVenue().getLon()));
                    }
                })
                .flatMap(new FlatMapFunction<RSVP, Tuple4<String, Integer, String, String>>() {
                    @Override
                    public void flatMap(RSVP rsvp, Collector<Tuple4<String, Integer, String, String>> collector) throws Exception {
                        collector.collect(new Tuple4(rsvp.getGroup().getGroup_city(), 1, rsvp.getVenue().getLat(), rsvp.getVenue().getLon()));
                    }
                })
                .keyBy(0)
                .sum(1)
                .flatMap(new FlatMapFunction<Tuple4<String, Integer, String, String>, String>() {
                    @Override
                    public void flatMap(Tuple4<String, Integer, String, String> favLocationsMax, Collector<String> collector) throws Exception {
                        collector.collect(String.join(";", favLocationsMax.f0, favLocationsMax.f1+"", favLocationsMax.f2, favLocationsMax.f3));
                    }
                })
                .addSink(new FlinkKafkaProducer011<String>(topic, new SimpleStringSchema(), properties));

        env.execute("Meetup RSVP Favourite Locations");
    }
}
