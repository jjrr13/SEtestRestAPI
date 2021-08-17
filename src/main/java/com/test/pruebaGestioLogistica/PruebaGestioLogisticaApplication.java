package com.test.pruebaGestioLogistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PruebaGestioLogisticaApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PruebaGestioLogisticaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password1 = "1213";
		String password2 = "123";
		System.out.println(passwordEncoder.encode(password1));
		System.out.println(passwordEncoder.encode(password2));
	}
}
