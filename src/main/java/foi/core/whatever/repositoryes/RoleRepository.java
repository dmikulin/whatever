package foi.core.whatever.repositoryes;

import java.util.List;

import foi.core.whatever.model.Role;

public interface RoleRepository {
	Role save(Role role);

	List<Role> findAll();

	Role findById(int id);

	Role findByName(String name);

	int update(Role role);

	int deleteById(int id);
}
