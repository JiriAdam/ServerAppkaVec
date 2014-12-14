package web.controller.account;

import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.UserRegForm;

import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(ModelMap model) {

        UserRegForm userForm = new UserRegForm();

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
    public String processRegistration(@ModelAttribute("userRegForm") UserRegForm user,
                                      ModelMap model) {

        // implement your own registration logic here...

        // for testing purpose:
        System.out.println("username: " + user.getUsername());
        System.out.println("password1: " + user.getPassword1());
        System.out.println("password2: " + user.getPassword2());
        System.out.println("email: " + user.getEmail());
        System.out.println("birth date: " + user.getBirthDate());

        model.addAttribute("message", "User " + user.getUsername() + " registered.");

        return "account/login";
    }



}
