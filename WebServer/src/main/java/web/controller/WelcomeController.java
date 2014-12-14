package web.controller;

import manager.UserManager;
import mybatis.dao.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WelcomeController {

	@Autowired
	private UserManager userManager;


	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		userManager.testMethod();

/*
		AppUser user =

		// for testing purpose:
		System.out.println("username: " + user.getUsername());
		System.out.println("password1: " + user.getPassword1());
		System.out.println("password2: " + user.getPassword2());
		System.out.println("email: " + user.getEmail());
		System.out.println("birth date: " + user.getBirthDate());
*/


		model.addAttribute("message", "Hello world!");
		return "hello";
	}

}