package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.Role;

public interface RoleService {
	Role save(Role role);

	List<Role> findAll();

	Role findById(int id);

	Role findByName(String name);

}
