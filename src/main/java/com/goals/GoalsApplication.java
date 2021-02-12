package com.goals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GoalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoalsApplication.class, args);
	}

	@PostConstruct
	public void runThings()
	{


	}

}
