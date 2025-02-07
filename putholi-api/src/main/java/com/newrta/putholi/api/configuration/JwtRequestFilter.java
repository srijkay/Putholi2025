package com.newrta.putholi.api.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.util.JwtUtil;
import com.newrta.putholi.api.util.MyUserDetailsService;

import lombok.Getter;
import lombok.Setter;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Component
@Getter
@Setter
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * 
     */
    @Autowired(required = true)
    private MyUserDetailsService userDetailsService;

    /**
     * 
     */
    @Autowired(required = true)
    private JwtUtil jwtUtil;

    /**
     * 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	    throws ServletException, IOException {
	final String authorizationHeader = request.getHeader(CommonsConstants.AUTHORIZATION);

	String username = null;
	String jwt = null;

	if (authorizationHeader != null && authorizationHeader.startsWith(CommonsConstants.BEARER)) {
	    jwt = authorizationHeader.substring(7);
	    username = jwtUtil.extractUsername(jwt);
	}

	if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

	    if (jwtUtil.validateToken(jwt, userDetails)) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken
			.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    }
	}
	chain.doFilter(request, response);
    }

}
