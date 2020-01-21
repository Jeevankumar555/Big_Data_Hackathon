package com.bigdata.meetup.rsvp.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Group implements Comparable
{
    private String group_name;

    private String group_city;

    private String group_lat;

    private String group_urlname;

    private String group_id;

    private String group_country;

    private String group_lon;

    private Group_topics[] group_topics;

    @Override
    public String toString()
    {
        return "Group [group_name = "+group_name+", group_city = "+group_city+", group_lat = "+group_lat+", group_urlname = "+group_urlname+", group_id = "+group_id+", group_country = "+group_country+", group_lon = "+group_lon+", group_topics = "+group_topics+"]";
    }


    @Override
    public boolean equals (Object o){
        Group g = (Group) o;
        return this.getGroup_id()== g.getGroup_id();
    }

    @Override
    public int compareTo(Object o) {
        Group g = (Group) o;
        if (this.getGroup_id()==g.getGroup_id())
            return 0;
        else return 1;
    }

}

