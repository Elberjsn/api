package com.atividade.manyToOne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class manyToOne {

	public static void main(String[] args) {
		SpringApplication.run(manyToOne.class, args);
	}

}
