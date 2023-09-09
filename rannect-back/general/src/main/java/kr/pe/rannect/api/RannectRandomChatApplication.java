package kr.pe.rannect.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RannectRandomChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(RannectRandomChatApplication.class, args);
	}

}
