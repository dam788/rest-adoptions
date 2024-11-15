package com.restful.adoptions;

import com.restful.adoptions.model.User;
import com.restful.adoptions.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

@SpringBootApplication
public class AdoptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionsApplication.class, args);
	}

}
