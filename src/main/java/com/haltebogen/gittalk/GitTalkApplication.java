package com.haltebogen.gittalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GitTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitTalkApplication.class, args);
	}



}
