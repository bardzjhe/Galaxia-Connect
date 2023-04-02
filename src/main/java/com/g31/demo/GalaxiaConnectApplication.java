package com.g31.demo;

<<<<<<< Updated upstream
import com.g31.demo.repository.UserRepository;
=======
import com.g31.demo.model.AuditUser;
import com.g31.demo.model.Role;
import com.g31.demo.model.RoleType;
import com.g31.demo.model.UserRole;
import com.g31.demo.repository.RoleRepository;
import com.g31.demo.repository.AuditUserRepository;
import com.g31.demo.repository.UserRoleRepository;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.g31.demo.repository"})
@EnableJpaRepositories("com.g31.demo.repository")
public class GalaxiaConnectApplication implements CommandLineRunner {

	@Autowired
<<<<<<< Updated upstream
	private UserRepository userRepository;
=======
	private RoleRepository roleRepository;
	@Autowired
	private AuditUserRepository auditUserRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
>>>>>>> Stashed changes

	public static void main(String[] args) {
		SpringApplication.run(GalaxiaConnectApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
<<<<<<< Updated upstream
		// TODO: initialize a user/admin
=======
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		// initialize role repository
		for (RoleType roleType : RoleType.values()) {
//			System.out.println(roleType.getName() + roleType.getDescription());
			roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
		}
		// initialize an admin account in user repository
		AuditUser auditUser = AuditUser.builder().userName("admin")
				.password(bCryptPasswordEncoder.encode("admin12345678"))
				.email("kevin@outlook.com").build();

		auditUserRepository.save(auditUser);
		Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
		userRoleRepository.save(new UserRole(auditUser, role));



>>>>>>> Stashed changes
	}
}
