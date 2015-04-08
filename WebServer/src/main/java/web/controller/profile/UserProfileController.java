package web.controller.profile;

import manager.UserManager;
import mybatis.model.complex.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Irrielde on 13.3.2015.
 */

@Controller
public class UserProfileController {

    @Autowired
    private UserManager userManager;


    @RequestMapping(value = "/profile/{user_id}",  method = RequestMethod.GET)
    public String yourEvents(@PathVariable Long user_id, ModelMap model, Principal principal ) {

        AppUser profile = userManager.getProfileFromID(user_id);

        model.addAttribute("profile", profile);

        return "profile/profile";
    }



}
