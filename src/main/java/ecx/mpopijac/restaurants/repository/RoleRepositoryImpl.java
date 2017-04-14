package ecx.mpopijac.restaurants.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ecx.mpopijac.restaurants.models.Role;

@Repository("roleRepository")
public class RoleRepositoryImpl implements RoleRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Role save(Role role) {
		try {
			em.persist(role);
			em.flush();
			return role;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Role> findAll() {
		try {
			return em.createQuery("select r from Role r").getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Role findById(int id) {
		return em.find(Role.class, id);
	}

	@Override
	public Role findByName(String name) {
		try {
			Query query = em.createQuery("select r from Role r where r.name = :name");
			query.setParameter("name", name);
			return (Role) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int update(Role role) {
		try {
			Query query = em.createQuery("update Role r set r.name=:name where r.id=:id");
			query.setParameter("name", role.getName());
			query.setParameter("id", role.getId());
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int deleteById(int id) {
		try {
			Query query = em.createQuery("delete from Role r where r.id=:id");
			query.setParameter("id", id);
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

}
