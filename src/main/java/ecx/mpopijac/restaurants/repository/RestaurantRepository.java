package ecx.mpopijac.restaurants.repository;

import java.util.List;

import ecx.mpopijac.restaurants.models.Restaurant;

public interface RestaurantRepository {
	Restaurant save(Restaurant restaurant);

	List<Restaurant> findByName(String name);

	Restaurant findId(int id);

	List<Restaurant> findAll();

	int deleteById(int id);

	int update(Restaurant restaurant);

}
