package com.fst.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.fst.elearning.repository")
@ComponentScan(basePackages = "com.fst.elearning")
public class PlateformeElearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlateformeElearningApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}
}