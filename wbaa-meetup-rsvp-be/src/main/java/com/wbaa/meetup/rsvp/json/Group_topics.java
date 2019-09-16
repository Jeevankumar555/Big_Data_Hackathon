package com.wbaa.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Group_topics
{
    private String urlkey;

    private String topic_name;

    @Override
    public String toString()
    {
        return "Group_topics [urlkey = "+urlkey+", topic_name = "+topic_name+"]";
    }

}