package com.wbaa.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RSVP
{
    private Member member;

    private String response;

    private String visibility;

    private Event event;

    private String mtime;

    private String guests;

    private String rsvp_id;

    private Group group;

    private Venue venue;

    @Override
    public String toString()
    {
        return "RSVP [member = "+member+", response = "+response+", visibility = "+visibility+", event = "+event+", mtime = "+mtime+", guests = "+guests+", rsvp_id = "+rsvp_id+", group = "+group+", venue = "+venue+"]";
    }
}