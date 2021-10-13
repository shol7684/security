package com.instar.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.instar.model.User;

import lombok.Data;

@Data
public class MyUserDetail implements UserDetails , OAuth2User{

	private User user;
	private Map<String, Object> attributes; 
	
	public MyUserDetail(User user) {
		System.out.println("생성자 1");
		this.user = user;
	}
	
	public MyUserDetail(User user, Map<String, Object> attributes) {
		System.out.println("생성자 2");
		this.user = user;
		this.attributes = attributes;
	}
	
	
	// 해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("디테일");
		
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		
		
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정 만료 안됨
	@Override
	public boolean isAccountNonExpired() { 
		return true;
	}

	// 계정이 잠기지 않음
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//	자격 증명이 만료되지 않음
	//  너무 오래 사용했나
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화
	@Override
	public boolean isEnabled() {
		// 계정이 
		
		return true;
	}

	// 아래부터 oaoth2User
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return null;
	}

}
