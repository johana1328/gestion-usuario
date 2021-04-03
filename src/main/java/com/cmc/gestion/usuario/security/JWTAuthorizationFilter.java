package com.cmc.gestion.usuario.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/login");
	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private JwtService jwt;

	@Autowired
	private Environment env;

	@Autowired
	private UserRequest userRequest;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			if (!this.requestMatcher.matches(request)) {
				if (existeJWTToken(request, response) && validateToken(request)) {
					setUpSpringAuthentication();
					Map<UserProperty, Object> propiedades = this.jwt.getUserRequest().getPropiedUser();
					this.userRequest.setPropiedUser(propiedades);
					chain.doFilter(request, response);
				} else {
					((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN,
							"Error usuario no autenticado");
					return;
				}
			}else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN,
					"Error usuario no autenticado");
			return;
		}

	}

	private void setUpSpringAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(this.jwt.extractUsername(),
				null,this.jwt.getAuthorities() );
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		String key = env.getProperty("com.cmc.security.key");
		int expriracion = Integer.parseInt(env.getProperty("com.cmc.security.timeout"));
		this.jwt = new JwtService(key, expriracion, jwtToken);
		if (!this.jwt.hasTokenExpired(jwtToken)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
			return false;
		}
		return true;
	}

}
