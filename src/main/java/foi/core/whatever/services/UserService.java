package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();
	
	List<User> findByActive(Boolean active);

	User findByUserId(int userId);

	User findByUsername(String username);

	User findByUsernameOrEmail(String name);

	User findByUsernameOrEmailAndActive(String name, boolean active);

	User findByEmail(String email);

	User findByUsernameAndPassword(String username, String password);

	User findByEmailAndPassword(String email, String password);

}
