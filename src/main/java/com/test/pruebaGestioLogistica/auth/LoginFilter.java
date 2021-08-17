package com.test.pruebaGestioLogistica.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pruebaGestioLogistica.entities.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/*
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authenticationManager) {
        super( new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        InputStream body = request.getInputStream();

        Usuario usuario = new ObjectMapper().readValue(body, Usuario.class);

        Authentication aun = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuario.getUsername(),
                        usuario.getPassword(),
                        Collections.emptyList()
                )
        );

        return aun;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        //super.successfulAuthentication(request, response, chain, authResult);
        JwtUtil.addAuthentication(response, authResult.getName());
    }
}
*/
