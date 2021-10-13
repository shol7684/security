package com.instar.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.instar.config.FacebookUserInfo;
import com.instar.config.GoogleUserInfo;
import com.instar.config.NaverUserInfo;
import com.instar.config.OAuth2UserInfo;
import com.instar.model.User;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Service
public class Oauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리 되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest.getClientRegistration() = " + userRequest.getClientRegistration());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());
		
		OAuth2UserInfo userInfo = null;
		
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			
			userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
			
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			
			userInfo = new FacebookUserInfo(oAuth2User.getAttributes());
			
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			
			userInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
			System.out.println("네이버 정보 =" + userInfo);
		} else {
			System.out.println("구글과 페이스북만 지원해요");
		}
		
		
		
		String provider = userInfo.getProvider();
		String providerId = userInfo.getProviderId();
		String email = userInfo.getEmail();
		String name = userInfo.getName();
		String username = provider + "_" + providerId;
		String password = bCryptPasswordEncoder.encode("랜덤"); 
		
		System.out.println(provider);
		System.out.println(providerId);
		System.out.println(email);
		
		
		User user = userService.joinCheck(username);
		
		System.out.println("user = "+ user);
		 
		if(user == null ) {
			user = new User();
			user.setProvider(provider);
			user.setProviderId(providerId);
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			
			userService.oauthJoin(user);
		}
		
		return new MyUserDetail(user, oAuth2User.getAttributes());
	}
	

}
