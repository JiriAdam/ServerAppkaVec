package web.controller.event.attendance;

import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.complex.AppUser;
import mybatis.model.basic.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Created by Irrielde on 4.1.2015.
 */
@Secured("BASIC_USER")
@Controller
public class AttendingEventController {


    @Autowired
    private EventManager eventManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ReservationManager reservationManager;


    @RequestMapping(value = "/user/attending", method = RequestMethod.GET)
    public String eventsIAmAttending(ModelMap model, Principal principal) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        List<Reservation> attendingEvents = reservationManager.getReservationsUserConfirmed(user.getId());


        model.addAttribute("attendingEvents", attendingEvents);

        return "event/attending_events";
    }

}
