package webservice.rest.controller.sync;

import manager.CountryManager;
import manager.EventFilterManager;
import manager.RegionManager;
import mybatis.model.basic.EventType;
import mybatis.model.complex.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

import java.util.List;

/**
 * Created by Irrielde on 31.3.2015.
 */

@RestController
public class SyncRestController {

    @Autowired
    private UserSessionManager userSessionManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private RegionManager regionManager;

    @Autowired
    private EventFilterManager eventFilterManager;


    @RequestMapping("/rest/get_types")
    public RestReply getAllEventTypes(@RequestParam(value="token", defaultValue="developer") String token) {

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        List<EventType> allTypes = eventFilterManager.getAllEventTypes();

        RestReply reply = new RestReply(0,"Ok");
        reply.setData(allTypes);

        return reply;

    }




    @RequestMapping("/rest/get_countries")
    public RestReply getAllCountries(@RequestParam(value="token", defaultValue="developer") String token) {

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        RestReply reply = new RestReply(0,"Ok");
        reply.setData(countryManager.getAllCountries());

        return reply;

    }

    @RequestMapping("/rest/get_regions")
    public RestReply getRegionsForCountry(@RequestParam(value="token", defaultValue="developer") String token,
                                     @RequestParam(value="country_id", defaultValue="60") Long countryID) {

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        List<Region> regionList = regionManager.getCountryRegionsByCountryId(countryID);

        if(regionList==null) new RestReply(4, "Invalid country ID");

        RestReply reply = new RestReply(0,"Ok");
        reply.setData(regionList);

        return reply;

    }


}
