package webservice.rest.controller.event;

import manager.CommentManager;
import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.basic.Reservation;
import notification.ObservablesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

import java.util.List;

/**
 * Created by Irrielde on 2.4.2015.
 */

@RestController
public class InvitationRestController {

    @Autowired
    private UserSessionManager userSessionManager;

    @Autowired
    private ReservationManager reservationManager;

    @Autowired
    private ObservablesManager observablesManager;

    private String usedDateFormat = "dd_MM_yyyy_HH_mm_ss";

    @RequestMapping(value = "/rest/invitation/get_all_pending", method = RequestMethod.GET)
    public RestReply getPendingInvites(@RequestParam(value = "token", defaultValue = "developer") String token,
                                       @RequestParam(value = "event_id", defaultValue = "15") Long eventID,
                                       @RequestParam(value = "attended", defaultValue = "yes") String yesNo){

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        List<Reservation> pendingInvitations = reservationManager.getMyPendingInvitations(userID);


        RestReply reply = new RestReply(0, "ok");
        reply.setData(pendingInvitations);


        return reply;
    }

    @RequestMapping(value = "/rest/invitation/handle", method = RequestMethod.GET)
    public RestReply handleInvite(@RequestParam(value = "token", defaultValue = "developer") String token,
                                  @RequestParam(value = "accept", defaultValue = "yes") String yesNo,
                                  @RequestParam(value = "invitation_id", defaultValue = "0") Long reservationID){

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        boolean accept = false;

        if(yesNo.equals("yes")){
            accept=true;
        }

        Boolean ok = false;

        if(accept){
            ok = reservationManager.confirmUsersReservation(reservationID, userID);
        }else{
            ok = reservationManager.declineUsersReservation(reservationID, userID);
        }

        if(ok){
            return new RestReply(0, "ok");
        }
        return new RestReply(3, "DB error");

    }


    //TODO public void sendInviteAtd();

}
