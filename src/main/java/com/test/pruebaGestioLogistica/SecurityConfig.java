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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;


@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
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
        http.csrf().disable()
                .cors().configurationSource( corsConfigurationSource ())
                .and()
                .authorizeRequests().antMatchers("/v2/api-docs", "/swagger-ui.html", "/**").permitAll()
                /*.antMatchers("/login").permitAll()*/
                .anyRequest().authenticated()
                .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))//Valida el loguin
                    .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))//validan el token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

   @Bean
    CorsConfigurationSource corsConfigurationSource() {
        System.out.println("Entro al security  ");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(false);
        //the below three lines will add the relevant CORS response headers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
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
