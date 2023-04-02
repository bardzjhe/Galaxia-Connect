package com.g31.demo;

import com.g31.demo.model.AuditUser;
import com.g31.demo.model.Role;
import com.g31.demo.model.RoleType;
import com.g31.demo.model.UserRole;
import com.g31.demo.repository.RoleRepository;
import com.g31.demo.repository.UserRepository;
import com.g31.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.g31.demo.repository"})
//@EnableJpaRepositories("com.g31.demo.repository")
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
	 * Initialize role repository and an admin.
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		// initialize role repository
		for (RoleType roleType : RoleType.values()) {
//			System.out.println(roleType.getName() + roleType.getDescription());
			roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
		}
		// initialize an admin account in user repository
		AuditUser auditUser = AuditUser.builder().userName("root")
				.password(bCryptPasswordEncoder.encode("root"))
				.email("kevin@outlook.com").build();
		userRepository.save(auditUser);
		Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
		userRoleRepository.save(new UserRole(auditUser, role));
	}
}
