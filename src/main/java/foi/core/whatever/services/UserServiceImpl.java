package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import foi.core.whatever.model.User;
import foi.core.whatever.repositoryes.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUserId(int userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByUsernameOrEmail(String name) {
		return userRepository.findByUsernameOrEmail(name, name);

	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);

	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findByUsernameOrEmailAndActive(String name, boolean active) {
		return userRepository.findByUsernameOrEmailAndActive(name, name, active);
	}

	@Override
	public List<User> findByActive(Boolean active) {
		return userRepository.findByActive(active);
	}

}
