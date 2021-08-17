package com.test.pruebaGestioLogistica;


import com.test.pruebaGestioLogistica.auth.filter.JWTAuthenticationFilter;
import com.test.pruebaGestioLogistica.auth.filter.JWTAuthorizationFilter;
import com.test.pruebaGestioLogistica.auth.handler.LoginSuccessHandler;
import com.test.pruebaGestioLogistica.auth.service.JWTService;
import com.test.pruebaGestioLogistica.entities.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JWTService jwtService;

    /**
     * Se configuran y se agregan los filtro para las peticiones, en este caso de hagregan dos filtros
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/v2/api-docs", "/swagger-ui.html").permitAll()
                /*.antMatchers("/login").permitAll()*/
            .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))//Valida el loguin
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))//validan el token
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Encoder la password para mayor seguirdad, requerida por spring-boot
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Se encarga de desencadenar la autenticacion con la base de datos de los usuarios
     * @param build
     * @throws Exception
     */
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
    {
        build.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


}
