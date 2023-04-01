package com.g31.demo;

import com.g31.demo.model.Role;
import com.g31.demo.model.RoleType;
import com.g31.demo.model.User;
import com.g31.demo.model.UserRole;
import com.g31.demo.repository.RoleRepository;
import com.g31.demo.repository.UserRepository;
import com.g31.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.g31.demo.repository"})
@EnableJpaRepositories("com.g31.demo.repository")
public class GalaxiaConnectApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(GalaxiaConnectApplication.class, args);
	}


	/**
	 * Initialize an admin.
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		// initialize an admin account in user repository
		User user = User.builder().userName("admin")
				.password(bCryptPasswordEncoder.encode("admin12345678"))
				.email("admin@galaxiaconnect.com").enabled(true).build();
		userRepository.save(user);
		Role role = roleRepository.findByName(RoleType.ADMIN.getRole()).get();
		userRoleRepository.save(new UserRole(user, role));
	}
}
