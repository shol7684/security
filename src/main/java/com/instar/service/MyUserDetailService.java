package com.instar.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.instar.controller.UserController;
import com.instar.dao.UserDAO;
import com.instar.model.User;

// 시큐리티 설정에서 loginProcessingUrl("/loginProc")
// 로그인 요청이 오면 MyUserDetailService 타입으로 ioc 되어있는 loadUserByUsername 함수가 실행

@Service
public class MyUserDetailService implements UserDetailsService {

	 private static final Logger LOGGER = LogManager.getLogger(MyUserDetailService.class);
	 
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		
		LOGGER.info(username);
		LOGGER.warn("123");
		
		User user = userDAO.login(username);
		System.out.println("user = " + user);
		if(user != null) {
			return new MyUserDetail(user);
		}
		
		return null;
	}

	
}
