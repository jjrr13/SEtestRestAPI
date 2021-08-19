package com.test.pruebaGestioLogistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class PruebaGestioLogisticaApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PruebaGestioLogisticaApplication.class, args);
	}

	/**
	 * Generea contraseñas encryptadas para guardar en la base de datos y hacer pruebas, intentar desacoplar esto y e
	 * en el la creacion de nuevo usuario generar y guardar en la BBDD
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		String password1 = "1213";
		String password2 = "123";
		System.out.println(passwordEncoder.encode(password1));
		System.out.println(passwordEncoder.encode(password2));
	}


}
