package ecx.mpopijac.restaurants.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ecx.mpopijac.restaurants.models.Restaurant;

@Repository("restaurantRepository")
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Restaurant save(Restaurant restaurant) {
		try{
			em.persist(restaurant);
			em.flush();
			return restaurant;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Restaurant> findByName(String name) {
		try{
			Query query = em.createQuery("select r from Restaurant r where r.name LIKE :name");
			query.setParameter("name", name);
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Restaurant findId(int id) {
		return em.find(Restaurant.class, id);
	}

	@Override
	public List<Restaurant> findAll() {
		try{
			return em.createQuery("select r from Restaurant r").getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public int deleteById(int id) {
		try{
			Query query = em.createQuery("delete from Restaurant r where r.id=:id");
			query.setParameter("id", id);
			return query.executeUpdate();
		}catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int update(Restaurant restaurant) {
		try{
			Query query = em.createQuery("update Restaurant r set r.name=:name, r.address=:address, r.description=:description where r.id=:id");
			query.setParameter("name", restaurant.getName());
			query.setParameter("address", restaurant.getAddress());
			query.setParameter("description", restaurant.getDescription());
			query.setParameter("id", restaurant.getId());
			return query.executeUpdate();
		}catch (Exception e) {
			return -1;
		}
	}

}
