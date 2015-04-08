package web.controller.event.invites;

import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.complex.AppUser;
import mybatis.model.basic.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Created by Irrielde on 4.1.2015.
 */
@Secured("BASIC_USER")
@Controller
public class InvitationPendingController {


    @Autowired
    private EventManager eventManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ReservationManager reservationManager;


        @RequestMapping(value = "/user/my_pending_invitations", method = RequestMethod.GET)
    public String ownerEventDetails(ModelMap model, Principal principal) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        List<Reservation> pendingInvites = reservationManager.getMyPendingInvitations(user.getId());


        model.addAttribute("pendingInvites", pendingInvites);

        return "event/your_pending_invites";
    }

    @RequestMapping(value = "/user/confirm_invite/{reservation_id}", method = RequestMethod.GET)
    public String confirmInvite(@PathVariable Long reservation_id, ModelMap model, Principal principal) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        Boolean confirmed = reservationManager.confirmUsersReservation(reservation_id, user.getId());

        if(confirmed){
            model.addAttribute("message","Confirmed.");
        }else{
            model.addAttribute("error","Error during confirming.");
        }

        List<Reservation> pendingInvites = reservationManager.getMyPendingInvitations(user.getId());
        model.addAttribute("pendingInvites", pendingInvites);

        return "event/your_pending_invites";
    }

    @RequestMapping(value = "/user/decline_invite/{reservation_id}", method = RequestMethod.GET)
    public String declineInvite(@PathVariable Long reservation_id, ModelMap model, Principal principal) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        Boolean deleted = reservationManager.declineUsersReservation(reservation_id, user.getId());

        if(deleted){
            model.addAttribute("message","Declined.");
        }else{
            model.addAttribute("error","Error during declining.");
        }

        List<Reservation> pendingInvites = reservationManager.getMyPendingInvitations(user.getId());
        model.addAttribute("pendingInvites", pendingInvites);

        return "event/your_pending_invites";
    }


}
