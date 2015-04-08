package web.controller.event;

import manager.CommentManager;
import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.complex.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.JsonConverter;
import web.model.map.MapDataFO;
import web.model.map.MapEventFO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 30.3.2015.
 */

@Controller
public class EventMapController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private ReservationManager reservationManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/public/map", method = RequestMethod.GET)
    public String publicEvents(ModelMap model) {

        List<Event> publicEvents =  eventManager.getAllPublicEvents();
        List<MapEventFO> eventsForMap = new LinkedList<MapEventFO>();


        for (int i = 0; i < publicEvents.size(); i++) {
            Event ev = publicEvents.get(i);
            MapEventFO mvFO = new MapEventFO();

            mvFO.setId(ev.getId());
            mvFO.setName(ev.getName());
            mvFO.setDescription(ev.getDescription());

            mvFO.setCity(ev.getAddress().getCity());
            mvFO.setCountry(ev.getAddress().getCountry());
            mvFO.setPostalCode(Integer.toString(ev.getAddress().getPostalCode()));
            mvFO.setRegion(ev.getAddress().getRegion());
            mvFO.setStreetName(ev.getAddress().getStreetName());
            mvFO.setRoute(Integer.toString(ev.getAddress().getStreetNumber()));

            mvFO.setLongitude(ev.getAddress().getLongitude());
            mvFO.setLatitude(ev.getAddress().getLatitude());

            eventsForMap.add(mvFO);

        }

       // model.addAttribute("publicEvents",publicEvents);

        MapDataFO map = new MapDataFO();
        map.setEvents(eventsForMap);

        String mapJson = JsonConverter.mapDataToJson(map);

        model.addAttribute("mapJson", mapJson);

        return "event/map";

    }

    @RequestMapping(value = "/public/map/filtered", method = RequestMethod.GET)
    public String publicEvents2(ModelMap model) {

        model.addAttribute("message","nejak prefiltruju");

        return "event/map";

    }

}
