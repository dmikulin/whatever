package ecx.mpopijac.restaurants.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecx.mpopijac.restaurants.models.Operation;
import ecx.mpopijac.restaurants.models.Role;
import ecx.mpopijac.restaurants.models.User;
import ecx.mpopijac.restaurants.service.RoleService;
import ecx.mpopijac.restaurants.service.UserService;

@Controller
public class SignUpController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpUser(Model model) {
		model.addAttribute("heading", "Sign Up");
		model.addAttribute("buttonAction", "Create an account");
		model.addAttribute("user", new User());
		model.addAttribute("operation", Operation.CREATE);
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "signup";
	}

	@RequestMapping(value = "/signup-error", method = RequestMethod.GET)
	public String signupError(Model model) {
		model.addAttribute("signupError", true);
		return "signup";
	}

	@RequestMapping(value = "/signup-user", method = RequestMethod.POST)
	public String SignUpUser(HttpServletRequest request, Model model) {

		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(bCryptPasswordEncoder.encode(request.getParameter("password")));
		user.setEmail(request.getParameter("email"));
		user.setRole(roleService.findById(Integer.parseInt(request.getParameter("role"))));
		userService.save(user);
		
		return "redirect:login";
	}

}
