package foi.core.whatever.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import foi.core.whatever.YaaSServices;
import foi.core.whatever.model.Cart;
import foi.core.whatever.model.Role;
import foi.core.whatever.model.User;
import foi.core.whatever.services.CartService;
import foi.core.whatever.services.RoleService;
import foi.core.whatever.services.UserService;

@Controller
public class SignUpController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	YaaSServices yaasServices;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static String UPLOADED_FOLDER = "C://Windows//Temp//";

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpUser(Model model) {
		model.addAttribute("heading", "Sign Up");
		model.addAttribute("buttonAction", "Create an account");
		model.addAttribute("user", new User());
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String SignUpUser(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws Exception {

		byte[] byteFile= null;
		FileInputStream fis;

		if (file.isEmpty()) {
			ClassLoader classLoader = getClass().getClassLoader();
			File oFile = new File(classLoader.getResource("static/img/default-avatar.png").getFile());
			try {
				byteFile = new byte[(int)oFile.length()];
				fis = new FileInputStream(oFile);
				fis.read(byteFile);
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else{
			try {
				byteFile = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, byteFile);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(bCryptPasswordEncoder.encode(request.getParameter("password")));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.addRoles(roleService.findByRoleName("User"));
		user.setAvatar(byteFile);
		user.setActive(false);
		user.setYaasId(yaasServices.newCustomer(user));		
		userService.save(user);
		
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setYaasId(yaasServices.newCart(cart));
		cartService.save(cart);
	
		return "redirect:login";
	}

}
