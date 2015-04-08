package webservice.rest.controller.user;

import manager.UserManager;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.FullProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webservice.rest.model.BodyData;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

import java.util.Base64;

/**
 * Created by Irrielde on 30.3.2015.
 */

@RestController
public class AccountRestController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserSessionManager userSessionManager;


    @RequestMapping(value = "/rest/account/upload_avatar", method = RequestMethod.POST)
    public RestReply setAvatar(@RequestParam(value="token", defaultValue="developer") String token,
                               @RequestBody BodyData data) {

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        AppUser user = new AppUser();
        user.setId(userID);

        byte[] decoded = Base64.getDecoder().decode(data.getStringValue());

        user.setAvatar(decoded);

       boolean ok = userManager.updateUserSelective(user);

        if(ok)  return new RestReply(0,"Ok");


        return new RestReply(3,"DB error");
    }

    @RequestMapping(value = "/rest/account/set_push_token", method = RequestMethod.POST)
    public RestReply setPushToken(@RequestParam(value="token", defaultValue="developer") String token,
                               @RequestBody BodyData data) {

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        AppUser user = new AppUser();
        user.setId(userID);


        user.setAndroidPushToken(data.getStringValue());

        boolean ok = userManager.updateUserSelective(user);

        if(ok)  return new RestReply(0,"Ok");


        return new RestReply(3,"DB error");
    }


    @RequestMapping(value = "/rest/account/update_account", method = RequestMethod.POST)
    public RestReply updateAccount(@RequestParam(value="token", defaultValue="developer") String token,
                                   @RequestBody AppUser user){

       // String token = "developer";

        Long userID =  userSessionManager.isAuthenticated(token);
        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        System.out.println(user.getEmail());
        user.setId(userID);


        boolean ok =  userManager.updateUserSelective(user);

        if(ok)  return new RestReply(0,"Ok");


        return new RestReply(3,"DB error");
    }



    @RequestMapping(value = "/rest/account/my_profile", method = RequestMethod.GET)
    public RestReply getMyProfile(@RequestParam(value="token", defaultValue="developer") String token){

        // String token = "developer";

        Long userID =  userSessionManager.isAuthenticated(token);

        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        FullProfile user =  userManager.getFullUserInfoFromID(userID);

        RestReply reply = new RestReply(0,"Ok");
        reply.setData(user);

        if(user!=null)  return reply;


        return new RestReply(3,"DB error");
    }



    @RequestMapping(value = "/rest/account/my_avatar", method = RequestMethod.GET)
    public RestReply getAvatarForProfileAsBase64(@RequestParam(value="token", defaultValue="developer") String token){

        Long userID =  userSessionManager.isAuthenticated(token);

        if(userID == -2L){
            return new RestReply(2,"Token expired... log in again.");
        }else if(userID == -1L){
            return new RestReply(1, "Invalid token");
        }

        FullProfile user =  userManager.getFullUserInfoFromID(userID);

        RestReply reply = new RestReply(0,"Ok");


        if(user!=null){
            reply.setData(user.getAvatarBase64());
            return reply;
        }


        return new RestReply(3,"DB error");
    }







}
