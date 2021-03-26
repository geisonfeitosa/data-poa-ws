package com.datapoaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataPoaWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataPoaWsApplication.class, args);
	}

}
