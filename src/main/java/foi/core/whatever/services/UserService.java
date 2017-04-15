package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.User;

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

}
