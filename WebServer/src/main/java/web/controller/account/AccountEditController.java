package web.controller.account;

import manager.UserManager;
import mybatis.model.complex.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.user.AccountFO;
import web.model.user.AvatarFO;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Irrielde on 13.3.2015.
 */

@Secured("BASIC_USER")
@Controller
public class AccountEditController {

    @Autowired
    private UserManager userManager;

    private String usedDateFormat = "dd/MM/yyyy";

    @RequestMapping(value = "/user/edit_account", method = RequestMethod.GET)
    public String getEditAccount(ModelMap model, Principal principal) {

        reloadForms(model, principal);

        return "account/edit_account";
    }


    @RequestMapping(value = "/user/edit_account", method = RequestMethod.POST)
    public String editAccountPOST(@ModelAttribute("accountForm") AccountFO accountFO, ModelMap model, Principal principal) {

        AppUser user = userManager.getUserFromUsername(principal.getName());

        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormat);

        try {
            Date dateFromString = sdf.parse(accountFO.getBirthDate());
            user.setBirthDate(dateFromString);
        } catch (ParseException e) {
            model.addAttribute("error", "Birthday format incorrect ["+usedDateFormat+"], rest is updated.");
            System.err.println(e.toString());
        }

        // user.setBirthDate(accountFO.getBirthDate());
        user.setEmail(accountFO.getEmail());
        user.setNotifyViaEmail(accountFO.getNotifyViaEmail());
        user.setNotifyViaPush(accountFO.getNotifyViaPush());

        boolean done = userManager.updateUser(user);

        if (done) {
            model.addAttribute("message", "Account updated.");
        } else {
            model.addAttribute("error", "Account not updated.");
        }

        reloadForms(model, principal);

        return "account/edit_account";
    }


    @RequestMapping(value = "/upload_avatar", method = RequestMethod.POST)
    public String saveAvatar(@ModelAttribute("avatarFile") AvatarFO uploadForm, ModelMap map, Principal principal) {

        MultipartFile file = uploadForm.getAvatar().get(0);

        String username = principal.getName();
        AppUser appUser = userManager.getUserFromUsername(username);

        try {
            appUser.setAvatar(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean done = userManager.updateUserWithBlobs(appUser);

        if (done) {
            map.addAttribute("message", "Avatar updated.");
        } else {
            map.addAttribute("error", "Avatar not updated.");
        }

        reloadForms(map, principal);

        return "account/edit_account";
    }


    private void reloadForms(ModelMap model, Principal principal) {

        AppUser user = userManager.getUserFromUsername(principal.getName());

        AccountFO accountFO = new AccountFO();

        accountFO.setEmail(user.getEmail());
        accountFO.setNotifyViaPush(user.getNotifyViaPush());
        accountFO.setNotifyViaEmail(user.getNotifyViaEmail());


        DateFormat df = new SimpleDateFormat(usedDateFormat);
        String birthDateFormatted = df.format(user.getBirthDate());

        accountFO.setBirthDate(birthDateFormatted);

        model.addAttribute("accountForm", accountFO);

    }


}
