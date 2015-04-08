package webservice.rest.controller.user;

import manager.EventFilterManager;
import manager.LocalityFilterManager;
import manager.RegionManager;
import mybatis.model.basic.EventType;
import mybatis.model.basic.LocalityFilter;
import mybatis.model.complex.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import web.model.EventFilterFO;
import webservice.rest.model.RegionRO;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Irrielde on 27.3.2015.
 */

@RestController
public class FilterRestController {

    @Autowired
    private UserSessionManager userSessionManager;

    @Autowired
    private LocalityFilterManager localityFilterManager;

    @Autowired
    private EventFilterManager eventFilterManager;


    @Autowired
    private RegionManager regionManager;

    @RequestMapping(value = "/rest/account/get_locality_filter", method = RequestMethod.GET)
    public RestReply getSubscribedRegions(@RequestParam(value = "token", defaultValue = "developer") String token) {


        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        List<Region> subscribedRegions = regionManager.getFilteredRegionsByUserId(userID);

        RestReply reply = new RestReply(0, "Ok");
        reply.setData(subscribedRegions);

        return reply;

    }

    /**
     * Method for subscribing or unsubscribing to regions.
     *
     * @param method subscribe / unsubscribe  as string ...
     */
    @RequestMapping(value = "/rest/account/change_locality_filter", method = RequestMethod.POST)
    public RestReply setSubscribedRegions(@RequestParam(value = "token", defaultValue = "developer") String token,
                                          @RequestParam(value = "method", defaultValue = "subscribe") String method,
                                          @RequestBody List<RegionRO> regions) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        Iterator<RegionRO> itrs = regions.iterator();

        System.out.println("Selected method = " + method);

        if (method.equalsIgnoreCase("subscribe")) {

            while (itrs.hasNext()) {

                RegionRO region = itrs.next();

                LocalityFilter newFilterSettings = new LocalityFilter();
                newFilterSettings.setIdUser(userID);
                newFilterSettings.setIdRegion(region.getId());

                try {
                    boolean ok = localityFilterManager.addUserFilterForRegion(newFilterSettings);
                    System.out.println("New locality filter (" + region.getName() + ") for [" + userID + "]  created: " + ok);

                } catch (DuplicateKeyException duplicateKeyException) {
                    System.out.println("DuplicateKeyException locality filter (" + region.getName() + ") for [" + userID + "], nothing new created");

                }
            }


        } else if (method.equalsIgnoreCase("unsubscribe")) {

            while (itrs.hasNext()) {

                RegionRO region = itrs.next();

                boolean ok = localityFilterManager.deleteUserFilterForRegion(userID, region.getId());

                System.out.println("Deleting locality filter (" + region.getName() + ") for [" + userID + "]  " + ok);
            }


        } else {
            return new RestReply(5, "Invalid method. Only subscribe or unsubscribe are valid");
        }

        return new RestReply(0, "Ok");


    }

    @RequestMapping(value = "/rest/account/get_event_type_filter", method = RequestMethod.GET)
    public RestReply getEventTypeSettings(@RequestParam(value = "token", defaultValue = "developer") String token) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }


        List<EventType> types = eventFilterManager.getEventFilterByUserId(userID);

        if (types == null) return new RestReply(3, "Error in DB");

        RestReply reply = new RestReply(0, "Ok");
        reply.setData(types);

        return reply;
    }

    @RequestMapping(value = "/rest/account/set_event_type_filter", method = RequestMethod.POST)
    public RestReply setEventTypeSettings(@RequestParam(value = "token", defaultValue = "developer") String token,
                                          @RequestBody List<String> typeNames) {


        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        EventFilterFO eventFilterForm = new EventFilterFO(typeNames);

        boolean ok = eventFilterManager.updateUserFilter(eventFilterForm, userID);

        if (!ok) return new RestReply(3, "Error in DB");

        return new RestReply(0, "Ok");
    }


}
