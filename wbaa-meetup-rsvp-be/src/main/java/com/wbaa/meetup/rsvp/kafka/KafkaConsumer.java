package com.wbaa.meetup.rsvp.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbaa.meetup.rsvp.json.FavouriteLocationsData;
import com.wbaa.meetup.rsvp.json.TrendingTopicsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "${kafka.topic.fav.loc}")
    public void consumeFavouriteLocationsData(@Payload String message) {
        String split[] = message.split(";");
        if(split.length == 4){
            FavouriteLocationsData favouriteLocationsData = new FavouriteLocationsData();
            favouriteLocationsData.setCity(split[0]);
            favouriteLocationsData.setWeight(split[1]);
            favouriteLocationsData.setLat(split[2]);
            favouriteLocationsData.setLon(split[3]);
            ObjectMapper Obj = new ObjectMapper();
            try {
                String jsonStr = Obj.writeValueAsString(favouriteLocationsData);
                template.convertAndSend("/famous/locations", jsonStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @KafkaListener(topics = "${kafka.topic.trending.topic}")
    public void consumeTrendingTopicsData(@Payload String message) {
        String split[] = message.split(";");
        if(split.length == 4){
            TrendingTopicsData trendingTopicsData = new TrendingTopicsData();
            trendingTopicsData.setCountry_code(split[0]);
            trendingTopicsData.setLat(split[1]);
            trendingTopicsData.setLon(split[2]);
            trendingTopicsData.setTopic(split[3]);
            ObjectMapper Obj = new ObjectMapper();
            try {
                String jsonStr = Obj.writeValueAsString(trendingTopicsData);
                template.convertAndSend("/famous/topics", jsonStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}