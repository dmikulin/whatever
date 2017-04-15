package foi.core.whatever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Role;
import foi.core.whatever.model.User;
import foi.core.whatever.services.RoleService;
import foi.core.whatever.services.UserService;

@Component
public class InitialData implements ApplicationRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		loadInitialData();
	}

	void loadInitialData() {
		Role role = roleService.findById(1);
		if (role == null) {
			role = new Role("Administrator");
			roleService.save(role);
		}

		User user = userService.findByUsername("admin");
		if (user == null) {
			user = new User("Admin", "Admin", "admin", "admin@email.com", bCryptPasswordEncoder.encode("admin"), role);
			userService.save(user);
		}

		role = roleService.findById(2);
		if (role == null) {
			role = new Role("User");
			roleService.save(role);
		}	
		
		user = userService.findByUsername("user");
		if (user == null) {
			user = new User("User", "User", "user", "user@email.com", bCryptPasswordEncoder.encode("user"), role);
			userService.save(user);
		}		
		
		

	}

}
