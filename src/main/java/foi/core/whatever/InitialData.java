package foi.core.whatever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Product;
import foi.core.whatever.model.Role;
import foi.core.whatever.model.User;
import foi.core.whatever.services.ProductService;
import foi.core.whatever.services.RoleService;
import foi.core.whatever.services.UserService;

@Component
public class InitialData implements ApplicationRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;	
	
	@Autowired
	private ProductService productService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		loadInitialData();
	}

	void loadInitialData() {
		Role role = new Role();
		role.setRoleName("Admin");
		roleService.save(role);

		role = new Role();
		role.setRoleName("User");
		roleService.save(role);
		
		User user = new User();
		user.setEmail("admin@email.com");
		user.setFirstName("Adminko");
		user.setLastName("Administratoric");
		user.setPassword(bCryptPasswordEncoder.encode("admin"));
		user.setUsername("admin");
		user.addRoles(roleService.findByRoleName("Admin"));
		userService.save(user);		
		
		user = new User();
		user.setEmail("ihorvat@email.com");
		user.setFirstName("Ivan");
		user.setLastName("Horvat");
		user.setPassword(bCryptPasswordEncoder.encode("ihorvat"));
		user.setUsername("ihorvat");
		user.addRoles(roleService.findByRoleName("User"));
		userService.save(user);
		
		Product product = new Product();
		product.setProductNumber("3321");
		product.setName("Canon EOS 77D");
		product.setDescription("Kada uzmete EOS 77D, značajke kao što je stražnji kontrolni kotačić i dodirni zaslon s promjenjivim kutom daju vam veću kontrolu i kreativnu slobodu. LCD zaslon na gornjoj strani omogućuje brzi pregled svih postavki fotoaparata.");
		product.setPriceEUR(848.00);
		product.setPriceUSD(899.99);
		productService.save(product);

		product = new Product();
		product.setProductNumber("9754");
		product.setName("Samsung Galaxy S7 edge");
		product.setDescription("Samsung Galaxy S7 Edge smartphone was launched in February 2016. The phone comes with a 5.50-inch touchscreen display with a resolution of 1440 pixels by 2560 pixels at a PPI of 534 pixels per inch.");
		product.setPriceEUR(632.00);
		product.setPriceUSD(669.99);
		productService.save(product);
		
		product = new Product();
		product.setProductNumber("6853");
		product.setName("iPhone 7 gold 32GB");
		product.setDescription("iPhone 7 dramatically improves the most important aspects of the iPhone experience. It introduces advanced new camera systems. The best performance and battery life ever in an iPhone.");
		product.setPriceEUR(725.00);
		product.setPriceUSD(769.00);
		productService.save(product);
	}

}
