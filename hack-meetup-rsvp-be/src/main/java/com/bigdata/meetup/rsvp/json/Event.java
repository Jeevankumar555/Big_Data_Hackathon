package com.bigdata.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event
{
    private String time;

    private String event_url;

    private String event_id;

    private String event_name;

    @Override
    public String toString()
    {
        return "Event [time = "+time+", event_url = "+event_url+", event_id = "+event_id+", event_name = "+event_name+"]";
    }
}

