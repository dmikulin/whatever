package ecx.mpopijac.restaurants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecx.mpopijac.restaurants.models.Role;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.repository.RoleRepository;

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

	@Transactional
	public ServiceStatus update(Role role) {
		return ServiceStatus.returnStatus(roleRepository.update(role));
	}

	@Transactional
	public ServiceStatus delete(Role role) {
		return ServiceStatus.returnStatus(roleRepository.deleteById(role.getId()));
	}
	
	@Transactional
	public ServiceStatus deleteById(int id) {
		return ServiceStatus.returnStatus(roleRepository.deleteById(id));
	}

}
