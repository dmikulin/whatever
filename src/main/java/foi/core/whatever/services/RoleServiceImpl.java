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

	@Override
	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	@Transactional
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	@Transactional
	public Role findByRoleId(int id) {
		return roleRepository.findByRoleId(id);
	}

	@Override
	@Transactional
	public Role findByRoleName(String name) {
		return roleRepository.findByRoleName(name);
	}

}
