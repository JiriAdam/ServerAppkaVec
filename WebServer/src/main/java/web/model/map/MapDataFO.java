package web.model.map;

import flexjson.JSON;
import mybatis.model.complex.Event;

import java.util.List;

/**
 * Created by Irrielde on 30.3.2015.
 */
public class MapDataFO {


    private List<MapEventFO> events;


    @JSON
    public List<MapEventFO> getEvents() {
        return events;
    }

    public void setEvents(List<MapEventFO> events) {
        this.events = events;
    }
}
