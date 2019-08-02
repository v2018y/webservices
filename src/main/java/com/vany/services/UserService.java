package com.vany.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vany.model.DAOUser;
import com.vany.repositeroy.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private HttpServletRequest request;
	

	public long getUserId() {
		String userId = request.getHeader("userId");
		System.out.println("Your user Id"+ userId);
//     	RestTemplate restTemplate =new RestTemplate();
//		DAOUser newData=restTemplate.getForObject("http://localhost:8081/user", DAOUser.class);
		return 0;
	}
}
