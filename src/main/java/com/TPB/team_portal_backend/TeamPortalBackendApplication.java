package com.TPB.team_portal_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TeamPortalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamPortalBackendApplication.class, args);
	}
}
