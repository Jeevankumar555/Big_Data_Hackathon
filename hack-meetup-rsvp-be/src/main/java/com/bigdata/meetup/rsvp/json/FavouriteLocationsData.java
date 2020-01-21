package com.bigdata.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavouriteLocationsData {
    String city;
    String weight;
    String lon;
    String lat;
}
