package com.bib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BibliothekApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliothekApplication.class, args);
	}

}
