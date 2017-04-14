package ecx.mpopijac.restaurants.repository;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ecx.mpopijac.restaurants.models.User;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public User save(User user) {
		try{
			em.persist(user);
			em.flush();
			return user;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		try{
			return em.createQuery("select u from User u").getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public User findByUsername(String username) {
		try {
			Query query = em.createQuery("select u from User u where u.username = :username");
			query.setParameter("username", username);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			Query query = em.createQuery("select u from User u where u.email=:email");
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try{
			Query query = em.createQuery("select u from User u where u.username=:username and u.password=:password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		try{
			Query query = em.createQuery("select u from User u where u.email=:email and u.password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int update(User user) {
		try{
			Query query = em.createQuery("update User u set u.firstName=:firstName, u.lastName=:lastName, u.username=:username, u.password=:password, u.email=:email, u.role=:role where u.id=:id");
			query.setParameter("firstName", user.getFirstName());
			query.setParameter("lastName", user.getLastName());
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			query.setParameter("email", user.getEmail());
			query.setParameter("role", user.getRole());
			query.setParameter("id", user.getId());
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int deleteById(int id) {
		try{
			Query query = em.createQuery("delete from User u where u.id=:id");
			query.setParameter("id", id);
			return query.executeUpdate();
		}catch (Exception e) {
			return -1;
		}
	}

	@Override
	public User findByUsernameOrEmail(String usernameOrEmail) {
		try{
			Query query = em.createQuery("select u from User u where u.username=:usernameOrEmail or u.email=:usernameOrEmail");
			query.setParameter("usernameOrEmail", usernameOrEmail);
			return (User) query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findAdminByUsernameOrEmail(String usernameOrEmail) {
		try{
			Query query = em.createQuery("select u from User u where (u.username=:usernameOrEmail or u.email=:usernameOrEmail) and u.role.id=:role");
			query.setParameter("usernameOrEmail", usernameOrEmail);
			query.setParameter("role", 1);
			return (User) query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

}
