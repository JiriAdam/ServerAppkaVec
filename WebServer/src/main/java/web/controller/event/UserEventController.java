package web.controller.event;

import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.basic.Reservation;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Event;
import notification.ObservablesManager;
import notification.ObserverNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.EventFO;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by Irrielde on 3.1.2015.
 */

//secured musi bejt pred controlem...
@Secured("BASIC_USER")
@Controller
public class UserEventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ReservationManager reservationManager;

    @Autowired
    private ObservablesManager observablesManager;

    @RequestMapping(value = "/user/events", method = RequestMethod.GET)
    public String yourEvents(ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);



        List<Event> ownersEvents = eventManager.getAllOwnersEvents(user.getId());

        model.addAttribute("ownersEvents", ownersEvents);

        return "event/your_events";
    }


    @RequestMapping(value = "/user/create_event", method = RequestMethod.GET)
    public String createEvent(ModelMap model) {

        EventFO eventForm = new EventFO();

        //command name in form:form commandName
        model.addAttribute("newEventForm", eventForm);

        return "event/create_event";
    }


    @RequestMapping(value = "/user/create_event", method = RequestMethod.POST)
    public String createEventFinish(@ModelAttribute("newEventForm") EventFO event,
                                    ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);
        event.setIdOwner(user.getId());

        System.out.println(event.getLatitude() + " " + event.getLongitude());

       // return "hello";

        if(event.getEventTypes().length==0){
            String[] typesDefault = {"casual"};
            event.setEventTypes(typesDefault);
        }

        Long returnedIdTest = eventManager.addEvent(event);

        String message = "Event["+returnedIdTest+"] creation with name '" + event.getName() +
                "' was successful. Event Type = " ;

        for (int i = 0; i < event.getEventTypes().length; i++) {
            message = message + event.getEventTypes()[i];

            if(i< event.getEventTypes().length -1 ){
                message = message + ", ";
            }else{
                message = message + ".";
            }

        }

        //Notify observers
  /*      ObserverNotification observerNotification = new ObserverNotification();
        Event eventToNotifyAbout = eventManager.getUsersEvent(returnedIdTest, user.getId());
        observerNotification.setEvent(eventToNotifyAbout);
        observablesManager.notifyObservers(observerNotification);
*/

        model.addAttribute("message", message);

        List<Event> ownersEvents = eventManager.getAllOwnersEvents(user.getId());

        model.addAttribute("ownersEvents", ownersEvents);

        return "event/your_events";

    }

    @RequestMapping(value = "/user/attend_public_event/{event_id}",  method = RequestMethod.GET)
    public String yourEvents(@PathVariable Long event_id, ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);


        Event event = eventManager.getPublicEvent(event_id);


        List<Reservation> allReservations = reservationManager.getReservationsForEvent(event_id);

        if(allReservations != null){
            if(allReservations.size() >= event.getCapacity()){

                List<Event> publicEvents =  eventManager.getAllPublicEvents();
                model.addAttribute("publicEvents",publicEvents);

                model.addAttribute("error", "This event reached its capacity.");

                return "event/public_events";
            }
        }





        Reservation reservation = new Reservation();

        if(event.getRequireConfirm()){
            reservation.setConfirmed(false);
            reservation.setConfirmPending(true);
        }else{
            reservation.setConfirmed(true);
            reservation.setConfirmPending(false);
        }

        reservation.setInvitationPending(false);
        reservation.setIdEvent(event_id);
        reservation.setTime(new Date());
        reservation.setIdUser(user.getId());




        Long reservationID = reservationManager.createReservation(reservation);

        if(reservationID>0){
            if(event.getRequireConfirm()) {
                model.addAttribute("message", "Reservation requested for confirmation with id: " + reservationID);
            }else{
                model.addAttribute("message","Reservation created with id: " + reservationID);
            }

        } else {
            model.addAttribute("error", "Reservation creation failed");
        }


        List<Event> publicEvents =  eventManager.getAllPublicEvents();
        model.addAttribute("publicEvents",publicEvents);


        return "event/public_events";

    }


}
