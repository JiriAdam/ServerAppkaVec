package web.controller.admin;

import manager.UserManager;
import mybatis.model.complex.AppUser;
import mybatis.model.enumeration.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Irrielde on 8.12.2014.
 */

@Secured("ROLE_ADMIN")
@Controller
public class AdminController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String basicAdmin(ModelMap model) {

        model.addAttribute("message", "Hello admin");

        return "admin/admin_hello";
    }

    @RequestMapping(value = "/admin/basic_users", method = RequestMethod.GET)
    public String getAllBasicUsers(ModelMap model) {


        List<AppUser> basicUsers = userManager.getUsersWithRole(RoleType.BASIC_USER);

        model.addAttribute("basicUsers", basicUsers);

        return "admin/basic_users";
    }



}