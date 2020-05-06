package com.zenon.UserActivities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan(basePackages = "com.zenon.model")
@EnableJpaRepositories(basePackages = {"com.zenon.repository"})
@ComponentScan(basePackages = {"com.zenon"})
public class UserActivitiesApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserActivitiesApplication.class, args);
	}

}
