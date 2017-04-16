package foi.core.whatever.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.User;
import foi.core.whatever.services.UserService;
import foi.core.whatever.model.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		final User user = userService.findByUsernameOrEmailAndActive(usernameOrEmail, true);
		if (user == null) {
			return new org.springframework.security.core.userdetails.User(" ", " ", new HashSet<>());
		}
		final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (final Role r : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(r.getRoleName().toUpperCase()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
