package web.controller.account;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        return "account/login";
    }

    @RequestMapping(value= "/account",method = RequestMethod.GET)
    public String displayAccount(ModelMap model) {

        return "account/account";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value= "/account/secured", method = RequestMethod.GET)
    public String displayAccountSecured(ModelMap model) {

        model.addAttribute("message", "Secured anotace na ROLE_ADMIN z controlleru");
        return "account/account";
    }

}