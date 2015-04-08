package web.controller.event;

import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.basic.Reservation;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Event;
import mybatis.model.complex.ReservationWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.InvitationsFO;

import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 4.1.2015.
 */

@Secured("BASIC_USER")
@Controller
public class OwnerEventController {


    @Autowired
    private EventManager eventManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ReservationManager reservationManager;


    @RequestMapping(value = "/user/my_event/{event_id}",  method = RequestMethod.GET)
    public String ownerEventDetails(@PathVariable Long event_id, ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);
        Event event = eventManager.getUsersEvent(event_id, user.getId());

        model.addAttribute("event", event);

        List<ReservationWithUser> reservations = reservationManager.getReservationsWithUsersForEvent(event_id);

        processReservationsWithUserAndAddToModel(reservations, model, user.getId());

        List<Reservation> reservations2 = reservationManager.getReservationsForEvent(event_id);

        Iterator<Reservation> itr = reservations2.iterator();

        //if user registered himself, he gets removed from view-model
        while (itr.hasNext()) {
            if (itr.next().getIdUser() == user.getId()) {
                itr.remove();
            }
        }


     //   System.out.println("Reservations count: " + reservations2.size());

        return "event/owner_event_details";
    }



    @RequestMapping(value = "/user/my_event/confirm_request/{event_id}/{reservation_id}",  method = RequestMethod.GET)
    public String confirmReservationRequest(@PathVariable Long event_id,@PathVariable Long reservation_id, ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        Event event = eventManager.getUsersEvent(event_id,user.getId());

        Boolean confirmed = reservationManager.confirmReservation(reservation_id);

        if(confirmed){
            model.addAttribute("message", "Request confirmed.");
        }else{
            model.addAttribute("error", "Error during confirming request");
        }


        model.addAttribute("event", event);

        List<Reservation> reservations = reservationManager.getReservationsForEvent(event.getId());

        processReservationsAndAddToModel(reservations,model,user.getId() );


        return "event/owner_event_details";
    }

    @RequestMapping(value = "/user/my_event/decline_request/{event_id}/{reservation_id}",  method = RequestMethod.GET)
    public String declineReservationRequest(@PathVariable Long event_id,@PathVariable Long reservation_id, ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        Event event = eventManager.getUsersEvent(event_id,user.getId());

        Boolean confirmed = reservationManager.declineReservation(reservation_id);

        if(confirmed){
            model.addAttribute("message", "Request declined.");
        }else{
            model.addAttribute("error", "Error during declining request");
        }


        model.addAttribute("event", event);

        List<Reservation> reservations = reservationManager.getReservationsForEvent(event.getId());



        processReservationsAndAddToModel(reservations,model,user.getId() );


        return "event/owner_event_details";
    }


    @RequestMapping(value = "/user/invite_to_event/{event_id}",  method = RequestMethod.GET)
    public String invitePeople(@PathVariable Long event_id, ModelMap model, Principal principal ) {

        //get logged user ID
        String name = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(name);

        Event event = eventManager.getUsersEvent(event_id, user.getId());
        model.addAttribute("event", event.getId());

        InvitationsFO invitationsForm = new InvitationsFO();

        model.addAttribute("invitationsForm", invitationsForm);

        return "event/send_invites";
    }

    @RequestMapping(value = "/user/invite_to_event",  method = RequestMethod.POST)
    public String invitePeopleProcess(@ModelAttribute("invitationsForm") InvitationsFO invitations, ModelMap model, Principal principal ) {

        String myName = principal.getName(); //get logged in username
        AppUser user = userManager.getUserFromUsername(myName);

        Event event = eventManager.getUsersEvent(invitations.getEventId(), user.getId());

        String[] usernames = invitations.getUsernames().split(",");

        boolean error = false;

        for (int i = 0; i < usernames.length; i++) {
            String username = usernames[i];
            AppUser invitedUser = userManager.getUserFromUsername(username);

            if(invitedUser != null){
                Reservation reservation = new Reservation();
                reservation.setIdEvent(event.getId());
                reservation.setIdUser(invitedUser.getId());
                reservation.setTime(new Date());
                reservation.setInvitationPending(true);
                reservation.setConfirmPending(false);
                reservation.setConfirmed(false);

                if(reservationManager.createReservation(reservation) < 0){
                    model.addAttribute("error", "Error during sending invitation to " + username);
                    error = true;
                }else{

                }

            }else{
                model.addAttribute("error", "User " + username + " not found.");
                error = true;
            }

        }

        if(!error){
            model.addAttribute("message","Invitations sent out successfully");
        }



        model.addAttribute("event", event);


        List<ReservationWithUser> reservations = reservationManager.getReservationsWithUsersForEvent(event.getId());

        processReservationsWithUserAndAddToModel(reservations, model, user.getId());

/*
        List<Reservation> reservations = reservationManager.getReservationsForEvent(event.getId());
        processReservationsAndAddToModel(reservations,model,user.getId() );
*/
        return "event/owner_event_details";

        // return "redirect:/user/my_event/"+invitations.getEventId();

    }

    /**
     * splits reservations into 3 lists: reservations(confirmed), confirmsPending and invitationsPending and adds them
     * to model as attributes
     * @param reservations list of reservation objects
     * @param model mvc model
     */
    private void processReservationsAndAddToModel(List<Reservation> reservations, ModelMap model, Long userId) {

        Iterator<Reservation> itrr = reservations.iterator();

        //if user registered himself, he gets removed from view-model
        while (itrr.hasNext()) {
            if (itrr.next().getIdUser().longValue() == userId.longValue()) {
                itrr.remove();
            }
        }

        List<Reservation> confirmsPending = new LinkedList<Reservation>();

        List<Reservation> invitationsPending = new LinkedList<Reservation>();

        //separate invitations, confirmPending and confirmed reservations
        Iterator<Reservation> itr = reservations.iterator();
        while(itr.hasNext()){

            Reservation reservation = itr.next();

            if(reservation.getConfirmPending()&&reservation.getInvitationPending()){
                model.addAttribute("error", "Reservation " + reservation.getId() + " has confirm and invitation as pending. This should not happen.");
            }

            if (reservation.getConfirmPending()){
                itr.remove();
                confirmsPending.add(reservation);
            }else if(reservation.getInvitationPending()){
                itr.remove();
                invitationsPending.add(reservation);
            }

        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("confirmsPending", confirmsPending);
        model.addAttribute("invitationsPending", invitationsPending);

    }
    /**
     * splits reservations into 3 lists: reservations(confirmed), confirmsPending and invitationsPending and adds them
     * to model as attributes
     * @param reservations list of reservation objects
     * @param model mvc model
     */
    private void processReservationsWithUserAndAddToModel(List<ReservationWithUser> reservations, ModelMap model, Long userId) {


        Iterator<ReservationWithUser> itrr = reservations.iterator();

        //if user registered himself, he gets removed from view-model
        while (itrr.hasNext()) {
            if (itrr.next().getUserId() == userId) {
                itrr.remove();
            }
        }




        List<ReservationWithUser> confirmsPending = new LinkedList<ReservationWithUser>();

        List<ReservationWithUser> invitationsPending = new LinkedList<ReservationWithUser>();

        //separate invitations, confirmPending and confirmed reservations
        Iterator<ReservationWithUser> itr = reservations.iterator();

     //   System.out.println("ReservationsWithUser count: " + reservations.size());

        while(itr.hasNext()){

            ReservationWithUser reservation = itr.next();

            if(reservation.getConfirmPending()&&reservation.getInvitationPending()){
                model.addAttribute("error", "Reservation " + reservation.getId() + " has confirm and invitation as pending. This should not happen.");
            }

            if (reservation.getConfirmPending()){
                itr.remove();
                confirmsPending.add(reservation);
            }else if(reservation.getInvitationPending()){
                itr.remove();
                invitationsPending.add(reservation);
            }

        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("confirmsPending", confirmsPending);
        model.addAttribute("invitationsPending", invitationsPending);
    }


}
