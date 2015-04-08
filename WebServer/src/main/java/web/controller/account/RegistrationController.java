package web.controller.account;

import manager.UserManager;
import mybatis.model.basic.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.UserFO;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(ModelMap model) {

        UserFO userForm = new UserFO();

        //command name in form:form commandName
        model.addAttribute("userRegForm", userForm);

        /*
        List<String> professionList = new LinkedList<>();
        professionList.add("Developer");
        professionList.add("Designer");
        professionList.add("IT Manager");
        model.put("professionList", professionList);
        */

        return "account/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userRegForm") UserFO user,
                                      ModelMap model) {

        // implement your own registration logic here...

       Long returnedIdTest = userManager.addUser(user);

        // for testing purpose:
      /*
        System.out.println("username: " + user.getUsername());
        System.out.println("password1: " + user.getPassword1());
        System.out.println("password2: " + user.getPassword2());
        System.out.println("email: " + user.getEmail());
        System.out.println("birth date: " + user.getBirthDate());
    */

        List<UserRole> roles = userManager.getUserRolesTest(returnedIdTest);

        model.addAttribute("message", "Your registration with username '" + user.getUsername() + "' was successful. ID(forTest) = " + returnedIdTest);

        return "account/login";
    }



}
