package webservice.rest.controller.user;

import mybatis.model.basic.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

import java.util.List;

/**
 * Created by Irrielde on 27.3.2015.
 */
@RestController
public class NotificationRestController {

    @Autowired
    private UserSessionManager userSessionManager;

    //TODO dodelat get notifications
    @RequestMapping("/rest/get_new_notifications")
    public RestReply getNewNotifications(@RequestParam(value="token", defaultValue="developer") String token){

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        //vytahnout notifikace

        List<Notification> notifications;

        RestReply reply = new RestReply(0,"Ok");
        reply.setData(null);

        return reply;

    }

}
