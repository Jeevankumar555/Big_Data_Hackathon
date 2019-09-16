package com.wbaa.meetup.rsvp.source;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbaa.meetup.rsvp.json.RSVP;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.IOException;
import java.net.URI;

public class RsvpStreamingSource implements SourceFunction<RSVP> {

    protected String uri;

    public RsvpStreamingSource(String uri) {
        this.uri = uri;
    }


    @Override
    public void run(final SourceContext<RSVP> sourceContext) throws Exception {
        final RsvpEndPoint clientEndPoint = new RsvpEndPoint(
                new URI(uri));
        clientEndPoint.addMessageHandler(new RsvpEndPoint.MessageHandler() {
            public void handleMessage(String message) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                RSVP event = null;
                try {
                    event = mapper.readValue(message, RSVP.class);
                    sourceContext.collect(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        while (true) {
            Thread.sleep(2000);
        }
    }

    @Override
    public void cancel() {
        try {
            System.out.println("cancel");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
