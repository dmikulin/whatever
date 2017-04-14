package ecx.mpopijac.restaurants.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecx.mpopijac.restaurants.models.Operation;
import ecx.mpopijac.restaurants.models.Restaurant;
import ecx.mpopijac.restaurants.service.RestaurantService;

@Controller
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@RequestMapping(value = "/crud-restaurant", method = RequestMethod.GET)
	public String crudRestaurantPage(Model model) {
		List<Restaurant> restaurants = restaurantService.findAll();
		for (Restaurant r : restaurants) {
			r.setDescription(r.getDescriptionAsPlainText());
		}
		model.addAttribute("restaurants", restaurants);
		return "crud-restaurant";
	}

	@RequestMapping(value = "/c-restaurant", method = RequestMethod.GET)
	public String createRestaurantPage(Model model) {
		model.addAttribute("heading", "Add new restaurant");
		model.addAttribute("buttonAction", "Add new restaurant");
		model.addAttribute("restaurant", new Restaurant());
		model.addAttribute("operation", "CREATE");
		return "cu-restaurant";
	}

	@RequestMapping(value = "/u-restaurant", method = RequestMethod.GET)
	public String updateRestaurantPage(HttpServletRequest request, Model model) {
		model.addAttribute("heading", "Update restaurant");
		model.addAttribute("buttonAction", "Update restaurant");
		Restaurant restaurant = restaurantService.findById(Integer.parseInt(request.getParameter("id")));
		restaurant.setDescription(restaurant.getDescriptionAsPlainText());
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("operation", "UPDATE");
		return "cu-restaurant";
	}

	// Fetch data and delete/create/update restaurant
	@RequestMapping(value = "/crud-restaurant", method = RequestMethod.POST)
	public String addCreateDeleteRestaurantPage(HttpServletRequest request, Model model) {
		Operation operation = Operation.valueOf(request.getParameter("operation"));
		switch (operation) {
		case DELETE: {
			Restaurant restaurant = new Restaurant();
			restaurant.setId(Integer.parseInt(request.getParameter("id")));
			restaurantService.delete(restaurant);
			break;
		}
		case CREATE: {
			Restaurant restaurant = new Restaurant();
			restaurant.setName(request.getParameter("name"));
			restaurant.setAddress(request.getParameter("address"));
			restaurant.setDescriptionAsHtml(request.getParameter("description"));
			restaurantService.save(restaurant);
			break;
		}
		case UPDATE: {
			Restaurant restaurant = new Restaurant();
			restaurant.setName(request.getParameter("name"));
			restaurant.setAddress(request.getParameter("address"));
			restaurant.setDescriptionAsHtml(request.getParameter("description"));
			restaurant.setId(Integer.parseInt(request.getParameter("id")));
			restaurantService.update(restaurant);
			break;
		}
		}
		List<Restaurant> restaurants = restaurantService.findAll();
		model.addAttribute("restaurants", restaurants);
		return "crud-restaurant";
	}
}
