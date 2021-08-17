package com.test.pruebaGestioLogistica.auth.filter;


import com.test.pruebaGestioLogistica.auth.service.JWTService;
import com.test.pruebaGestioLogistica.auth.service.JWTServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Se encarga de gestionar y desencadenar el proceso de la autorizacion a los recursos de la pagina que se tengan configuradas
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	/**
	 * Crear el filtro interno para que sea reconocido por el contenedor de springboot
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(JWTServiceImpl.HEADER_STRING);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = null;
		
		if(jwtService.validate(header)) {
			authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null, jwtService.getRoles(header));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);//Autentica el usuario en la peticion
		chain.doFilter(request, response);
		
	}

	/**
	 * Valida la si el header continen una autorizacion de tipo Bearer
	 * @param header
	 * @return
	 */
	protected boolean requiresAuthentication(String header) {

		if (header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}

}
