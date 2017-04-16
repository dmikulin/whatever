package foi.core.whatever.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.Role;
import foi.core.whatever.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleId(int roleId);

	Role findByRoleName(String roleName);

	List<User> findUserByRoleId(int roleId);

}
