package foi.core.whatever;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.Product;
import foi.core.whatever.model.ProductCategory;
import foi.core.whatever.model.Role;
import foi.core.whatever.model.User;
import foi.core.whatever.services.CartService;
import foi.core.whatever.services.ProductCategoryService;
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
	private CartService cartService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	YaaSServices yaasServices;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		if(roleService.findAll().size()==0){
			loadInitialRoles();
		}
		if(userService.findAll().size()==0){
			loadInitialUsers();
		}		
		if(productCategoryService.findAll().size()==0){
			loadInitialCategories();
		}		
		if(productService.findAll().size()==0){
			loadInitialProducts();
		}
		yaasServices.getAllProducts();
	}

	private void loadInitialCategories() {
		ProductCategory category = new ProductCategory();
		category.setName("Zacin");
		productCategoryService.save(category);

		category = new ProductCategory();
		category.setName("Cokolada");
		productCategoryService.save(category);

		category = new ProductCategory();
		category.setName("Vitaminski napitak");
		productCategoryService.save(category);

		category = new ProductCategory();
		category.setName("Keksi");
		productCategoryService.save(category);
	}

	private void loadInitialProducts() throws ClientProtocolException, IOException {
		Product product = new Product();
		product.setProductNumber("5d105039");
		product.setName("Vegeta - Podravka 1kg");
		product.setCategory(productCategoryService.findByName("Zacin"));
		product.setDescription("Bogatstvo okusa. 100% kvalitetan odabir bez dodatnih aroma, bez konzervansa. Za inspiraciju i slobodu da pripremiše sve što zamisliš. Odvaži se i započni nove kulinarske priče.");
		product.setPriceEUR(3.35);
		product.setPriceUSD(3.65);
		productService.save(product);

		yaasServices.newProduct(product);

		product = new Product();
		product.setProductNumber("223d4a39");
		product.setName("Mliječna čokolada - Dorina 250g");
		product.setCategory(productCategoryService.findByName("Cokolada"));
		product.setDescription("Kraševa vrhunska mliječna čokolada kreirana je za prave poznavatelje slastica. Aromatičnog, bogatog okusa, izrađena od najboljih i najkvalitetnijih sastojaka sinonim je za kvalitetu kojoj generacije potrošača neupitno vjeruje.");
		product.setPriceEUR(2.95);
		product.setPriceUSD(3.20);
		productService.save(product);

		yaasServices.newProduct(product);

		product = new Product();
		product.setProductNumber("40e4667c");
		product.setName("Cedevita okus limun 1kg");
		product.setCategory(productCategoryService.findByName("Vitaminski napitak"));
		product.setDescription("Prirodna aroma - 9 vitamina. Od 1969. Cedevita je vaš omiljeni napitak jedinstvenog okusa i izvor vitamina: C, B2, B6, B12, niacina, pantotenske kiseline i folne kiseline koji doprinose smanjenju umora i iscrpljenosti.");
		product.setPriceEUR(5.35);
		product.setPriceUSD(5.83);
		productService.save(product);

		yaasServices.newProduct(product);

		product = new Product();
		product.setProductNumber("f0b55c7c");
		product.setName("Napolitanke s čokoladnim punjenjem - Kraš 840g");
		product.setCategory(productCategoryService.findByName("Keksi"));
		product.setDescription("Vrhunska kombinacija prhkih vafl listova i slasne kreme od čokolade rezultat je Kraševog dugogodišnjeg iskustva i tradicije u proizvodnji vafl proizvoda.");
		product.setPriceEUR(3.36);
		product.setPriceUSD(3.67);
		productService.save(product);

		yaasServices.newProduct(product);

	}

	private void loadInitialUsers() throws ClientProtocolException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource("static/img/admin-avatar.png").getFile());
		byte[] byteFile = new byte[(int)file.length()];

		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			fis.read(byteFile);
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		User user = new User();
		user.setEmail("admin@email.com");
		user.setFirstName("Adminko");
		user.setLastName("Administratoric");
		user.setPassword(bCryptPasswordEncoder.encode("admin"));
		user.setUsername("admin");
		user.setPhone("098 511 1517");
		user.addRoles(roleService.findByRoleName("Admin"));
		user.addRoles(roleService.findByRoleName("User"));
		user.setAvatar(byteFile);
		userService.save(user);	

		yaasServices.newCustomer(user);

		Cart cart = new Cart();
		cart.setUser(user);
		cartService.save(cart);

		file = new File(classLoader.getResource("static/img/default-avatar.png").getFile());
		byte[] byteFile1 = new byte[(int)file.length()];

		try {
			fis = new FileInputStream(file);
			fis.read(byteFile1);
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		user = new User();
		user.setEmail("ihorvat@email.com");
		user.setFirstName("Ivan");
		user.setLastName("Horvat");
		user.setPassword(bCryptPasswordEncoder.encode("ihorvat"));
		user.setUsername("ihorvat");
		user.setPhone("098 594 5197");
		user.addRoles(roleService.findByRoleName("User"));
		user.setAvatar(byteFile1);
		userService.save(user);

		yaasServices.newCustomer(user);

		cart = new Cart();
		cart.setUser(user);
		cartService.save(cart);
	}

	private void loadInitialRoles() {
		Role role = new Role();
		role.setRoleName("Admin");
		roleService.save(role);

		role = new Role();
		role.setRoleName("User");
		roleService.save(role);		
	}


}
