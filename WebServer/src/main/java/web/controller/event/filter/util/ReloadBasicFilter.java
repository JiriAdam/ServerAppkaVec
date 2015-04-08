package web.controller.event.filter.util;

import manager.CountryManager;
import manager.RegionManager;
import manager.UserManager;
import mybatis.model.basic.Country;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Region;
import mybatis.model.complex.UserEventFilter;
import org.springframework.ui.ModelMap;
import web.model.EventFilterFO;
import web.model.LocalityFilterFO;
import web.model.filter.CountryWithRegionsFO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 26.3.2015.
 */

/**
 * Just to reduce duplicity
 */
public class ReloadBasicFilter {

    public static void reloadBasicFilter(ModelMap model,  AppUser user, UserManager userManager,
                                   CountryManager countryManager, RegionManager regionManager) {

        //get logged user ID
        UserEventFilter userEventFilter = userManager.getUserEventFilterFromUsername(user.getUsername());

        EventFilterFO eventFilterForm = new EventFilterFO();
        eventFilterForm.setFilterFromList(userEventFilter.getEventTypes());
        model.addAttribute("eventFilterForm", eventFilterForm);


        //get country list:
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

        //prepare new form:

        LocalityFilterFO fo = new LocalityFilterFO();
        model.addAttribute("localityForm", fo);

        //get actual locality filter:

        List<Region> subscribedRegions = regionManager.getFilteredRegionsByUserId(user.getId());
        if(subscribedRegions==null) return;
        List<CountryWithRegionsFO> countryWithRegionsFOs = new LinkedList<CountryWithRegionsFO>();
        CountryWithRegionsFO countryWithRegionsFO = new CountryWithRegionsFO();
        List<Region> countryRegions = new LinkedList<Region>();

        for (int i = 0; i < subscribedRegions.size(); i++) {

            Region region = subscribedRegions.get(i);
         //   System.out.println("Found: " + region.getName() + "  in country: " + region.getIdCountry());

            if(countryWithRegionsFO.getCountry()==null){
                countryWithRegionsFO.setCountry(region.getCountry());
         //       System.out.println("Zeme: " + region.getCountry().getName());
            }

            if(countryWithRegionsFO.getCountry().getId().longValue() != region.getIdCountry().longValue()){


          //      System.out.println("Zakladam novou zemi:");

                countryWithRegionsFOs.add(countryWithRegionsFO);
                countryWithRegionsFO.setRegions(countryRegions);

                //init new
                countryWithRegionsFO = new CountryWithRegionsFO();
                countryRegions = new LinkedList<Region>();



            }

       //     System.out.println("Pridavam: " + region.getName());
            countryRegions.add(region);


            if(countryWithRegionsFO.getCountry()==null){
                countryWithRegionsFO.setCountry(region.getCountry());
          //      System.out.println("Zeme: " + region.getCountry().getName());
            }

        }

        //and save last
        countryWithRegionsFOs.add(countryWithRegionsFO);
        countryWithRegionsFO.setRegions(countryRegions);


        /*
        for (int i = 0; i < countryWithRegionsFOs.size(); i++) {
            System.out.println("Country: " + countryWithRegionsFOs.get(i).getCountry().getId());

            for (int j = 0; j < countryWithRegionsFOs.get(i).getRegions().size(); j++) {
                System.out.println("Region: " +  countryWithRegionsFOs.get(i).getRegions().get(j).getName());
            }
        }
        */


        model.addAttribute("actualLocalityFilter", countryWithRegionsFOs);

    }



}
