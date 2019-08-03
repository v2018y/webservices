package com.vany.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private HttpServletRequest request;
	

	public long getUserId() {
		String userId = request.getHeader("userId");
		System.out.println("Your user Id : "+ userId);
		return Long.parseLong(userId);
	}
}
