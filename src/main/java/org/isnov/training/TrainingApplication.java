package org.isnov.training;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@SpringBootApplication
@CrossOrigin(maxAge = 150000, origins = "*")
public class TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

	@PostConstruct
	public void sayHello(){
		log.warn("Bonjour mon app");
	}

	@PreDestroy
	private void sayGoodBye(){
		log.warn("Good Bye");
	}

}
