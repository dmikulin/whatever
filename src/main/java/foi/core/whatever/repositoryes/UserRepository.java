package foi.core.whatever.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import foi.core.whatever.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int userId);

	User findByUsername(String username);
	
	List<User> findByActive(Boolean active);

	User findByUsernameOrEmail(String username, String email);

	@Query("select u from User u where (u.username =?1 or u.email = ?2) and u.active=?3")
	User findByUsernameOrEmailAndActive(String username, String email, boolean active);

	User findByEmail(String email);

	User findByUsernameAndPassword(String username, String password);

	User findByEmailAndPassword(String email, String password);

}
