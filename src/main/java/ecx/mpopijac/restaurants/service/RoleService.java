package ecx.mpopijac.restaurants.service;

import java.util.List;

import ecx.mpopijac.restaurants.models.Role;
import ecx.mpopijac.restaurants.models.ServiceStatus;

public interface RoleService {
	Role save(Role role);

	List<Role> findAll();

	Role findById(int id);

	Role findByName(String name);

	ServiceStatus update(Role role);

	ServiceStatus delete(Role role);
	
	ServiceStatus deleteById(int id);
}
