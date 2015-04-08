package web.controller;


import manager.EventManager;
import manager.RegionManager;
import mybatis.model.basic.Address;
import mybatis.model.complex.Event;
import mybatis.model.complex.Region;
import notification.ObservablesManager;
import notification.ObserverNotification;
import notification.apitest.GoogleTest;
import util.ApplicationContextProvider;
import notification.email.MailMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.ListPrinter;

import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private RegionManager regionManager;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private ObservablesManager observablesManager;


    private int eventsPerPage = 4;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("message", "Index");
        return "hello";

    }


    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(ModelMap model) {
        return "test3";
    }

    @RequestMapping(value = "/mybatis", method = RequestMethod.GET)
    public String testMyBatis(ModelMap model) {

        List<Event> events = eventManager.getAllPublicEventsPageThatHaventStarted(0,eventsPerPage);

        ListPrinter.printList(events,"Prvni strana");

        List<Event> events2 = eventManager.getAllPublicEventsPageThatHaventStarted(1,eventsPerPage);

        ListPrinter.printList(events2,"Druha strana");

        Long totalEvents = eventManager.getNumberOfEventsThatHaventStarted();

        Long totalPages = 0L ;

        while(totalEvents > totalPages*eventsPerPage){
            totalPages = totalPages+1L;
        }

        System.out.println(totalEvents+"/"+eventsPerPage+"="+totalPages);
        System.out.println("Number of pages: " + totalPages);

        return "hello";
    }

        @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index2(ModelMap model) {

		/*
        GoogleTest gt = new GoogleTest();

		List<Region> regionList = regionManager.getAllRegions();

		System.out.println("pocet regionu: ");
		System.out.println(regionList.size());
		int badCounter = 0;
		int skipped =  0;
		for (int i = 0; i< regionList.size(); i++) {
			Region reg = regionList.get(i);



			if(reg.getArea1()!=null){
			//	System.out.println(reg.getId() + " /name: " + reg.getName() + " /google_name: " + reg.getGoogleName());


			}else{
				System.out.println(reg.getId() + " /name: " + reg.getName() + " /google_name: NULL");
			}

			//skip if has gname
			if(reg.getArea1()!=null){
			//	System.out.println("Skipping");
				skipped++;
				continue;
			}



				String gName = gt.getGoogleNameForRegion(reg.getName()+ " " + reg.getCountry().getName());

				if(gName==null){
					System.out.println("ERROR-NULL");
				}else{
					reg.setArea1(gName);
					boolean updated = regionManager.updateRegion(reg);
					System.out.println("<<<<< New google name: " + gName + " saved? " + updated);


				}



			System.out.println();


		}

		//model.addAttribute("message", "Pocet nenastavenych regionu: " + badCounter);
		model.addAttribute("message", "Pocet preskocenych regionu: " + skipped);

		return "hello";
*/
        return "test";
    }

    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String testGoogleApi(ModelMap model) {
        GoogleTest gt = new GoogleTest();

        model.addAttribute("message", gt.test());
        return "hello";

    }

    @RequestMapping(value = "/observer", method = RequestMethod.GET)
    public String observerTest(ModelMap model) {


        model.addAttribute("message", "Count observables: " + observablesManager.initObservers());

        return "hello";

    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String emailTest(ModelMap model) {


        ObserverNotification observerNotification = new ObserverNotification();
        Event evEmpty = new Event();

        Address adr = new Address();
        adr.setCity("Testovaci mesto lol");
        adr.setIdRegion(947L);

        evEmpty.setAddress(adr);
        observerNotification.setEvent(evEmpty);

        observablesManager.notifyObservers(observerNotification);


        model.addAttribute("message", "Zijem");

        return "hello";

    }

}