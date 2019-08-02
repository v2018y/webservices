package com.vany.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping(value="/")
    public String viewHome() {
//		String username;
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//		  username = ((UserDetails)principal).getUsername();
//		} else {
//		  username = principal.toString();
//		}
//		System.out.println("User name : "+ username);
        return "<h1>Yes... Running...</h1>";
    }
}
