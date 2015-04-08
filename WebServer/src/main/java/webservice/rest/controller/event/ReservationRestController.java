package webservice.rest.controller.event;

import manager.CommentManager;
import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.basic.Reservation;
import mybatis.model.complex.Event;
import notification.ObservablesManager;
import notification.enumeration.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webservice.rest.model.BodyData;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Irrielde on 27.3.2015.
 */

//TODO ReservationRestController
@RestController
public class ReservationRestController {

    @Autowired
    private UserSessionManager userSessionManager;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private ReservationManager reservationManager;

    @Autowired
    private ObservablesManager observablesManager;

    private String usedDateFormat = "dd_MM_yyyy_HH_mm_ss";



    @RequestMapping(value = "/rest/reservation/reserve", method = RequestMethod.GET)
    public RestReply createReservationForEvent(@RequestParam(value = "token", defaultValue = "developer") String token,
                                               @RequestParam(value = "event_id", defaultValue = "15") Long eventID){

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        Event event = eventManager.getPublicEvent(eventID);


        List<Reservation> allReservations = reservationManager.getReservationsForEvent(eventID);

        if(allReservations != null){
            if(allReservations.size() >= event.getCapacity()){

                return new RestReply(7, "This event reached its capacity.");
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
        reservation.setIdEvent(eventID);
        reservation.setTime(new Date());
        reservation.setIdUser(userID);




        Long reservationID = reservationManager.createReservation(reservation);

        if(reservationID>0){

            if(event.getRequireConfirm()) {
                return new RestReply(8, "ok... awaiting event's owner for confirmation");
            }else{
                return new RestReply(0, "ok");
            }

        }else if(reservationID==-2L){

            return new RestReply(10, "Reservation already exists.");

        }else{
            return new RestReply(3, "DB error");
        }



    }

    @RequestMapping(value = "/rest/reservation/cancel", method = RequestMethod.GET)
    public RestReply cancelReservationForEvent(@RequestParam(value = "token", defaultValue = "developer") String token,
                                               @RequestParam(value = "event_id", defaultValue = "15") Long eventID){

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        boolean canceled = reservationManager.cancelReservationByEventUserIDs(userID, eventID);

        if(canceled){
            return new RestReply(0, "ok");
        }

        return new RestReply(9, "No such reservation existed.");
    }

    @RequestMapping(value = "/rest/reservation/attended", method = RequestMethod.GET)
    public RestReply setAttended(@RequestParam(value = "token", defaultValue = "developer") String token,
                                 @RequestParam(value = "event_id", defaultValue = "15") Long eventID,
                                 @RequestParam(value = "attended", defaultValue = "yes") String yesNo){

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        boolean attended = false;

        if(yesNo.equals("yes")){
            attended=true;
        }

        int statusCode = reservationManager.setAttendedForReservation(userID, eventID, attended);

        switch (statusCode){
            default:
            case 0:
                return new RestReply(0, "ok");
            case 1:
                return new RestReply(9, "No such reservation existed.");
            case 2:
                return new RestReply(3, "DB error");
        }



    }




    /**
     * Gets all confirmed reservations
     * If event is set to require_confirm = true, owner of that event must first confirm your request. Once owner
     * does so, that reservation will appear in this list.
     * Requests for reservation are ignored until confirmed by event's owner
     * @param token
     * @return
     */
    @RequestMapping(value = "/rest/reservation/get_all_confirmed", method = RequestMethod.GET)
    public RestReply getAllMyReservations(@RequestParam(value = "token", defaultValue = "developer") String token){
        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        List<Reservation> reservations = reservationManager.getReservationsUserConfirmed(userID);

        RestReply reply = new RestReply(0, "ok");
        reply.setData(reservations);

        return reply;
    }





}
