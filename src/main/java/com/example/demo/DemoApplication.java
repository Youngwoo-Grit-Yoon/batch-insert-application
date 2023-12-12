package com.example.demo;

import com.example.demo.config.VdnConfiguration;
import com.example.demo.jpa.Vdn;
import com.example.demo.jpa.VdnRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(VdnRepository vdnRepository, VdnConfiguration vdnConfiguration) {
		return (args) -> {

		};
	}
}
