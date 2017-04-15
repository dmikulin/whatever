package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import foi.core.whatever.model.Role;
import foi.core.whatever.repositoryes.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Transactional
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Transactional
	public Role findById(int id) {
		return roleRepository.findById(id);
	}

	@Transactional
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}


}
