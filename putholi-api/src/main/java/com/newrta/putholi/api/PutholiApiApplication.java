package com.newrta.putholi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@SpringBootApplication
@EnableScheduling
@PropertySource("file:${external.config.location}")
public class PutholiApiApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PutholiApiApplication.class, args);
	}

}
