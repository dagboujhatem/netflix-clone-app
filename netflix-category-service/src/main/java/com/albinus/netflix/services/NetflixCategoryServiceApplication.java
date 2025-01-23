package com.albinus.netflix.services;

import com.albinus.netflix.lib.models.Category;
import com.albinus.netflix.services.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EntityScan(basePackages = "com.albinus.netflix.lib.models")
public class NetflixCategoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixCategoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CategoryRepository categoryRepository){
		return args -> {
			categoryRepository.deleteAll();
			Stream.of("Action", "Drama", "Comedy", "Horror", "Science Fiction (Sci-Fi)")
					.forEach(category -> categoryRepository.save(new Category(null, category, null)));
		};
	}
}
