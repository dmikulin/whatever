package ecx.mpopijac.restaurants;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.Restaurant;
import ecx.mpopijac.restaurants.models.Role;
import ecx.mpopijac.restaurants.models.User;
import ecx.mpopijac.restaurants.service.ArticleService;
import ecx.mpopijac.restaurants.service.CommentService;
import ecx.mpopijac.restaurants.service.RestaurantService;
import ecx.mpopijac.restaurants.service.RoleService;
import ecx.mpopijac.restaurants.service.UserService;

@Component
public class InitialData implements ApplicationRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		loadInitialData();
	}

	void loadInitialData() {
		Role role = roleService.findById(1);
		if (role == null) {
			role = new Role("Administrator");
			roleService.save(role);
		}

		User user = userService.findByUsername("admin");
		if (user == null) {
			user = new User("Admin", "Admin", "admin", "admin@email.com", bCryptPasswordEncoder.encode("admin"), role);
			userService.save(user);
		}

		role = roleService.findById(2);
		if (role == null) {
			role = new Role("User");
			roleService.save(role);
		}	
		
		user = userService.findByUsername("user");
		if (user == null) {
			user = new User("User", "User", "user", "user@email.com", bCryptPasswordEncoder.encode("user"), role);
			userService.save(user);
		}		
		
		role = roleService.findById(3);
		if (role == null) {
			role = new Role("Unregistered user");
			roleService.save(role);
		}

		user = userService.findByEmail("unregistred.user1@email.com");
		if (user == null) {
			user = new User("Unregistred", "User", "unregistred.user1@email.com");
			userService.save(user);
		}

		int counter;
		while (restaurantService.findAll().size() < 5) {
			counter = restaurantService.findAll().size() + 1;
			restaurantService.save(new Restaurant(" El Celler de Can Roca " + counter, "Girona, Spain",
					"El Celler de Can Roca is a restaurant in Girona, Catalonia, Spain which was opened in 1986 by the Roca brothers, Joan, Josep and Jordi. It was first located next to their parent's restaurant Can Roca, but moved to its current purpose built building in 2007. It has been received warmly by critics, and holds three Michelin stars. In 2013, it was named the best restaurant in the world by the magazine Restaurant, after having been ranked second in 2011 and 2012. In 2014, it was named second best restaurant in the world. In 2015, it was once again named the best restaurant in the world by the magazine Restaurant."));
		}

		Restaurant restaurant = restaurantService.findById(1);
		User author = userService.findById(1);
		while (articleService.findAll().size() < 5) {
			counter = articleService.findAll().size() + 1;
			String description = "The cuisine served by the restaurant is traditional Catalan, but with twists to the extent that the Michelin Guide describes it as 'creative'. The restaurant has a large wine cellar with some 60,000 bottles. Dishes served include those based on perfumes, and with unusual presentations such as caramelised olives served on a bonsai tree. <br/>    El Celler de Can Roca was founded in 1986 by the Roca brothers next to their family's main restaurant Can Roca which had been open on the site since 1967. The oldest brother, Joan Roca is the head chef; Josep Roca, the middle brother, is the sommelier, and the youngest brother, Jordi Roca, is in charge of desserts. Because of their work at the restaurant, the brothers have appeared at Harvard University in the United States as part of the Science and Cooking program. <br/>    In late 2007 the restaurant moved to a modern building custom-built for the restaurant about 100 meters from the prior location, with the original location still being used for staff meals. The new layout features wooden floors, with simply dressed tables. On each table sits three stones to signify the three Roca brothers, while the tableware is Rosenthal china. The enlarged kitchen in the new location includes space for thirty chefs to work and also features Joan Roca's open plan office, so that he can keep an eye on the chefs even while he is doing more administrative tasks. At least one of the three brothers is present for every service. There is a capacity for 45 diners.";
			articleService.save(new Article("El Celler de Can Roca " + counter,
					"." + File.separator + "upload" + File.separator + "El_Celler_de_Can_Roca_exterior.jpg",
					description, restaurant, author));
		}

		Article article = articleService.findById(1);
		while (commentService.findAll().size() < 5) {
			counter = commentService.findAll().size() + 1;
			commentService.save(new Comment(
					"Wow. Sounds and looks amazing. Beautifully written – I feel like I’ve just savoured a morsel of each dish. Which is almost certainly as close as I’ll ever get to actually eating there!",
					true, author, article));
			commentService.save(new Comment(
					"Great review. I have a booking for lunch in Dec and I am so looking forward to it. However mine is for lunch and I hope lunch time quality and standards are the same with the brothers in the kitchen. <br/> Then after which I will be going to Barcelona with a dinner booking at ABaC. However I am wondering if I should try Lasarte instead. Your thoughts would be much appreciated! <br/> Best <br/> Joanne",
					true, author, article));
		}

	}

}
