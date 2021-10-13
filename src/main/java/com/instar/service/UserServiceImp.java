package com.instar.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instar.controller.UserController;
import com.instar.dao.UserDAO;
import com.instar.model.User;

@Service
public class UserServiceImp implements UserService{
	
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImp.class);


	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void join(User user) {
		
		LOGGER.info("회원가입 정보" + user );
		
		
		userDAO.join(user);
	}

	@Override
	public User joinCheck(String username) {
		return userDAO.joinCheck(username);
	}

	@Override
	public void oauthJoin(User user) {
		userDAO.oauthJoin(user);
	}
	

}
