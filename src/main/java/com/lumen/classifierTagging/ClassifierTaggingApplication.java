package com.lumen.classifierTagging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClassifierTaggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassifierTaggingApplication.class, args);
	}

}
