package web.controller.event.filter;

import manager.CountryManager;
import manager.EventFilterManager;
import manager.RegionManager;
import manager.UserManager;
import mybatis.model.basic.Country;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Region;
import mybatis.model.complex.UserEventFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.controller.event.filter.util.ReloadBasicFilter;
import web.model.EventFilterFO;
import web.model.LocalityFilterFO;
import web.model.filter.CountryWithRegionsFO;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 26.3.2015.
 */
@Secured("BASIC_USER")
@Controller
public class FilterController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private RegionManager regionManager;


    @RequestMapping(value = "/user/filter", method = RequestMethod.GET)
    public String basicFilter(ModelMap model, Principal principal ) {

        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        ReloadBasicFilter.reloadBasicFilter(model, user, userManager, countryManager, regionManager);

        return "event/filter/event_filter";
    }


}
