package ecx.mpopijac.restaurants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecx.mpopijac.restaurants.models.Restaurant;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.repository.RestaurantRepository;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Transactional
	public Restaurant save(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	@Transactional
	public Restaurant findById(int id) {
		return restaurantRepository.findId(id);
	}

	@Transactional
	public List<Restaurant> findByName(String name) {
		return restaurantRepository.findByName(name);
	}

	@Transactional
	public ServiceStatus delete(Restaurant restaurant) {
		return ServiceStatus.returnStatus(restaurantRepository.deleteById(restaurant.getId()));
	}

	@Transactional
	public ServiceStatus update(Restaurant restaurant) {
		return ServiceStatus.returnStatus(restaurantRepository.update(restaurant));
	}

	@Transactional
	public ServiceStatus deleteById(int id) {
		return ServiceStatus.returnStatus(restaurantRepository.deleteById(id));
	}
}
