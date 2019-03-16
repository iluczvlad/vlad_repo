package com.vlad.backend;

import com.vlad.backend.model.Role;
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
		checkUser(context, "admin@admin.admin", Role.ADMIN);
		checkUser(context, "basic@admin.admin", Role.BASIC);
		checkUser(context, "clerk@admin.admin", Role.CLERK);
	}

	private static void checkUser(ConfigurableApplicationContext context, String email, Role role) {
		UserRepository userRepository = context.getBean(UserRepository.class);
		User admin = userRepository.findByEmail(email);
		if (admin == null){
			User user = new User();
			user.setEmail(email);
			user.setRole(role);
			PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
			user.setPassword(passwordEncoder.encode("admin"));
			userRepository.save(user);
		}
	}
}
