package com.instar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.instar.service.Oauth2UserService;


@Configuration
@EnableWebSecurity //스프링 시큐리티 필터에 등록하는 어노테이션
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true) // secure,prePostEnabled 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Oauth2UserService oauth2UserService;
	
	// 1. Bean 어노테이션은 메서드에 붙여서 객체 생성시 사용
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 2. 필터링
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() // 로그인만 하면 들어갈수 있는 주소
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()  // 위 등록된 주소가 아니면 다 허용
			.and()
				.formLogin()
				.loginPage("/auth/login") //권한 없는 사람이 요청했을땐 로그인페이지로 이동
				.loginProcessingUrl("/auth/loginProc") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 해줌
				.defaultSuccessUrl("/") // 성공시 / 페이지로 이동
			.and()
				.oauth2Login()
				.loginPage("/auth/loginProc")
				.defaultSuccessUrl("/main") // 성공시 / 페이지로 이동
				.userInfoEndpoint()
				.userService(oauth2UserService)
				
				;
		

		
//		 MyUserDetailService loadUserByUsername 파라미터 유저네임 바꾸고싶을때
//		.usernameParameter("username2")
		
		
		
		
		
		
		
//		http.csrf().disable();
//		http.cors().disable();
//		http.authorizeRequests()
//		.antMatchers("/", "/user/**", "/follow/**", "/image/**") // 이런 주소로 들어오게 되면 인증이 필요
//		.authenticated()
//		.anyRequest()
//		.permitAll()
//		.and()
//		.formLogin()
//		.loginPage("/auth/login")
//		.loginProcessingUrl("/auth/loginProc")
//		.successHandler(new AuthenticationSuccessHandler() {
//			
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//				response.sendRedirect("/");
//			}
//		});
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// 내가 인코딩하는게 아니라, 어떤 인코딩으로 패스워드가 만들어졌는지 알려주는 거야!!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}
	
	



}