package com.vednexgen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.vednexgen"})
public class SpringBootCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCourseApplication.class, args);
	}

	/**
	 * This method is to inspect the beans provided by Spring Boot application on
	 * initialization
	 *
	 * @param ctx ApplicationContext object
	 * @return
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			String[] beanNames = ctx.getBeanDefinitionNames();
			IO.println("Let's inspect the beans provided by Spring Boot, total beans found : " + beanNames.length);
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				IO.println(beanName);
			}

		};
	}

}
