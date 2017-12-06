package com.koitt.board.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("customUserDetailsService")
public class CustomUserDetailsService extends BasicAuthenticationEntryPoint{
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException{
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader("www-Authenticate", "Basic realm" + getRealmName() + "");
		
		PrintWriter writer = response.getWriter();
		writer.println("Http Status 401 : " + authenticationException.getMessage());
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception{
		setRealmName("MY_TEST_REALM");
		super.afterPropertiesSet();
	}
}
