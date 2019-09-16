package com.wbaa.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrendingTopicsData {
    String country_code;
    String lat;
    String lon;
    String topic;
}
