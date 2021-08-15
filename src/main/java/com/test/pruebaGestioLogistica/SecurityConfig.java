package com.test.pruebaGestioLogistica;

import com.test.pruebaGestioLogistica.auth.JwtFilter;
import com.test.pruebaGestioLogistica.auth.LoginFilter;
import com.test.pruebaGestioLogistica.entities.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
                .and()
                .addFilterBefore( new LoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore( new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class) ;
    }


    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Creamos una cuenta de usuario por default ( cambiar por base de datos)

       *//* auth.inMemoryAuthentication()
                .withUser("srJJ")
                .password("1213")
                .roles("ADMIN");*//*
    }
*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Autowired
    public void configure(AuthenticationManagerBuilder build) throws Exception
    {
        build.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM usuarios WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.nombre FROM roles r " +
                        "INNER JOIN usuario_roles ur ON (r.id = ur.id_rol ) " +
                        "INNER JOIN usuarios u ON ( ur.id_usuario = u.id ) " +
                        "WHERE u.username = ? ");
    }*/

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
    {
        build.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


}
