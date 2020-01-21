package com.bigdata.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Venue
{
    private String lon;

    private String venue_name;

    private String venue_id;

    private String lat;

    @Override
    public String toString()
    {
        return "Venue [lon = "+lon+", venue_name = "+venue_name+", venue_id = "+venue_id+", lat = "+lat+"]";
    }

}

