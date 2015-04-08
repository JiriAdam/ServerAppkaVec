package web.controller.event.filter;

import manager.CountryManager;
import manager.EventFilterManager;
import manager.RegionManager;
import manager.UserManager;
import mybatis.model.basic.Country;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.UserEventFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.controller.event.filter.util.ReloadBasicFilter;
import web.model.EventFilterFO;
import web.model.LocalityFilterFO;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 11.3.2015.
 */
@Secured("BASIC_USER")
@Controller
public class EventTypeFilterController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private EventFilterManager eventFilterManager;

    @Autowired
    private RegionManager regionManager;


    private void reloadBasicFilter(ModelMap model,  AppUser user) {

        //get logged user ID
        UserEventFilter userEventFilter = userManager.getUserEventFilterFromUsername(user.getUsername());

        EventFilterFO eventFilterForm = new EventFilterFO();
        eventFilterForm.setFilterFromList(userEventFilter.getEventTypes());
        model.addAttribute("eventFilterForm", eventFilterForm);


        List<Country> countryList = countryManager.getAllCountries();

        //Put Cze to top
        Country cze = null;
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            if(country.getName().equalsIgnoreCase("Czech Republic")){
                cze = country;
                countryList.remove(i);
                break;
            }
        }
        countryList.add(0, cze);


        model.addAttribute("countryList", countryList);

        LocalityFilterFO fo = new LocalityFilterFO();

        model.addAttribute("localityForm", fo);

    }

    @RequestMapping(value = "/user/filter", method = RequestMethod.POST)
    public String updateFilter(@ModelAttribute("eventFilterForm") EventFilterFO eventFilterForm,
                                  ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        eventFilterManager.updateUserFilter(eventFilterForm, user.getId());
        model.addAttribute("message", "Event filter updated.");

        ReloadBasicFilter.reloadBasicFilter(model, user, userManager, countryManager, regionManager);


        return "event/filter/event_filter";
    }


}
