package com.liniitriesit.shopgenerator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.antMatchers(HttpMethod.POST, "/registration").permitAll()
				.anyRequest()
				.authenticated()
				.and()			
			.formLogin().disable();
//			.formLogin()
//				.failureUrl("/login.html?error=true")
//				.defaultSuccessUrl("/shops/1",true)
//				.permitAll()
//			.and()
//			.logout()
//				.logoutUrl("/logout.html")
//			.permitAll();
		http.httpBasic();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.userDetailsService(userDetailsService)
//			.passwordEncoder(encoder())
//			;
//	}
	
//	@Override
//	public void configure(WebSecurity web) {
//		web
//			.ignoring()
//			.antMatchers("/resources/**")
//			.antMatchers("/css/**")
//			.antMatchers("/js/**")
//			.antMatchers("/image/**");
//	}
	
	
}
