package ecx.mpopijac.restaurants.service;

import java.util.List;

import ecx.mpopijac.restaurants.models.Restaurant;
import ecx.mpopijac.restaurants.models.ServiceStatus;

public interface RestaurantService {

	Restaurant save(Restaurant restaurant);

	List<Restaurant> findAll();

	Restaurant findById(int id);

	List<Restaurant> findByName(String name);

	ServiceStatus delete(Restaurant restaurant);
	
	ServiceStatus deleteById(int id);

	ServiceStatus update(Restaurant restaurant);

}
