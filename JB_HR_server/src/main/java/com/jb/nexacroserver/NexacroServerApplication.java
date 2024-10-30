package com.jb.nexacroserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NexacroServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexacroServerApplication.class, args);
	}

}
