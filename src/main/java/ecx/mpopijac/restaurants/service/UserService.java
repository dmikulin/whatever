package ecx.mpopijac.restaurants.service;

import java.util.List;

import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.models.User;

public interface UserService {
	User save(User user);

	List<User> findAll();

	User findById(int id);

	User findByUsername(String username);

	User findByEmail(String email);
	
	User findByUsernameOrEmail(String usernameOrEmail);
	
	User findAdminByUsernameOrEmail(String usernameOrEmail);

	User findByUsernameAndPassword(String username, String password);

	User findByEmailAndPassword(String email, String password);

	ServiceStatus update(User user);

	ServiceStatus delete(User user);
}
