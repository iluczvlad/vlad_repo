package com.vlad.backend;

import com.vlad.backend.model.User;
import com.vlad.backend.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BackendApplication.class, args);
		init(context);
	}

	private static void init(ConfigurableApplicationContext context){
		UserRepository userRepository = context.getBean(UserRepository.class);
		User admin = userRepository.findByEmail("admin@admin.admin");
		if (admin == null){
			User user = new User();
			user.setEmail("admin@admin.admin");
			PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
			user.setPassword(passwordEncoder.encode("admin"));
			userRepository.save(user);
		}
	}
}
