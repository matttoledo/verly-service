package com.verly.verlyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VerlyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerlyServiceApplication.class, args);
	}

}
