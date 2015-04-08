package web.controller.event.filter;

import manager.*;
import mybatis.model.basic.Country;
import mybatis.model.basic.LocalityFilter;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Region;
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
import web.model.RegionFO;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 23.3.2015.
 */

@Secured("BASIC_USER")
@Controller
public class LocalityFilterController {

    @Autowired
    private LocalityFilterManager localityFilterManager;

    @Autowired
    private RegionManager regionManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private CountryManager countryManager;

    @Autowired
    private EventFilterManager eventFilterManager;

    @RequestMapping(value = "/user/locality_filter", method = RequestMethod.GET)
    public String localityFilterGET(ModelMap model, Principal principal) {

        //get logged user ID
        String name = principal.getName(); //get logged in username

        //defaultni hodnota pro ceskou republiku 60L


        List<Region> userLocalityFilter = regionManager.getFilteredRegionsByUsername(name);
        List<Region> regionList = regionManager.getCountryRegionsByCountryId(60L);

        model.addAttribute("country_id", 60L);

        generateLocalityFilterFromUser(userLocalityFilter, regionList, model);


        return "event/filter/locality_filter";

    }

    @RequestMapping(value = "/user/locality_filter", method = RequestMethod.POST)
    public String localityFilterPOST(@ModelAttribute("localityForm") LocalityFilterFO localityForm,
                                     ModelMap model, Principal principal) {


        //get logged user ID
        String name = principal.getName(); //get logged in username

        //prepare checkboxes from user filter ...
        List<Region> userLocalityFilter = regionManager.getFilteredRegionsByUsername(name);
        List<Region> regionList = regionManager.getCountryRegionsByCountryId(localityForm.getId());

        model.addAttribute("country_id", localityForm.getId());

        generateLocalityFilterFromUser(userLocalityFilter, regionList, model);

        return "event/filter/locality_filter";
    }


    @RequestMapping(value = "/user/locality_update", method = RequestMethod.POST)
    public String localityFilterUpdatePOST(@ModelAttribute("localityUpdate") LocalityFilterFO localityForm,
                                           ModelMap model, Principal principal) {

        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        Long countryID = localityForm.getId();

        List<LocalityFilter> actualFilter = localityFilterManager.getUserLocalityFilterForCountry(user.getId(),countryID );

        List<Long> handledFilter = new LinkedList<Long>();

        int localityLen = 0;
        if(localityForm.getRegions()!=null){
            localityLen = localityForm.getRegions().length;
        }

        //1 2 3 4
        for (int j = 0; j < localityLen; j++) {


            Long fromModel = localityForm.getRegions()[j];

            boolean match = false;

            // 3 4 5 6
            for (int i = 0; i < actualFilter.size(); i++) {

                Long fromFilter = actualFilter.get(i).getIdRegion();
                if (fromModel.longValue() == fromFilter.longValue()) {
                    match = true;
                    break;
                }


            }
            handledFilter.add(fromModel);

            //add new filter
           if (!match) {

               LocalityFilter lf = new LocalityFilter();
               lf.setIdRegion(fromModel);
               lf.setIdUser(user.getId());
               localityFilterManager.addUserFilterForRegion(lf);
            }


        }

        for (int i = 0; i < actualFilter.size(); i++) {

            boolean toDelete = true;

            for (int j = 0; j < handledFilter.size(); j++) {
                if (actualFilter.get(i).getIdRegion().longValue() == handledFilter.get(j).longValue()) {
                    toDelete = false;
                }
            }

            //delete old filter
            if(toDelete){
                localityFilterManager.deleteUserFilterForRegion(user.getId(), actualFilter.get(i).getIdRegion());
            }


        }

        //reloadBasicFilter(model, user);

        ReloadBasicFilter.reloadBasicFilter(model, user, userManager, countryManager, regionManager);

        model.addAttribute("message", "Event filter updated.");


        return "event/filter/event_filter";
    }

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

    private void generateLocalityFilterFromUser(List<Region> userLocalityFilter, List<Region> regionList, ModelMap model) {


        List<RegionFO> regionFOs = new LinkedList<RegionFO>();

        // System.out.println("user filter: " + userLocalityFilter.size());
        // System.out.println("total regions to filter: " + regionList.size());


        for (int i = 0; i < regionList.size(); i++) {
            Region region = regionList.get(i);
            RegionFO rfo = new RegionFO();

            rfo.setId(region.getId());
            rfo.setName(region.getName());
            rfo.setArea1(region.getArea1());
            rfo.setChecked(false);

            for (int j = 0; j < userLocalityFilter.size(); j++) {
                Region userRegion = userLocalityFilter.get(j);
                if (userRegion.getId().longValue() == region.getId().longValue()) {
                    rfo.setChecked(true);
                    break;
                }
            }

            regionFOs.add(rfo);

        }


        LocalityFilterFO newLocalityFilterFO = new LocalityFilterFO();
        model.addAttribute("localityUpdate", newLocalityFilterFO);

        model.addAttribute("regionCheckboxes", regionFOs);

    }


}
