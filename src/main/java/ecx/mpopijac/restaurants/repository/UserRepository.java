package ecx.mpopijac.restaurants.repository;

import java.util.List;

import ecx.mpopijac.restaurants.models.User;

public interface UserRepository {
	User save(User user);

	List<User> findAll();

	User findById(int id);

	User findByUsername(String username);
	
	User findAdminByUsernameOrEmail(String usernameOrEmail);

	User findByEmail(String email);

	User findByUsernameAndPassword(String username, String password);

	User findByEmailAndPassword(String email, String password);

	int update(User user);

	int deleteById(int id);

	User findByUsernameOrEmail(String usernameOrEmail);
}
