package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Role;
import foi.core.whatever.model.User;
import foi.core.whatever.services.RoleService;
import foi.core.whatever.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/crud-user", method = RequestMethod.GET)
	public String crudUserPage(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "crud-user";
	}

	@RequestMapping(value = "/c-user", method = RequestMethod.GET)
	public String createUserPage(Model model) {
		model.addAttribute("heading", "Add new user");
		model.addAttribute("buttonAction", "Add new user");
		model.addAttribute("user", new User());
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "cu-user";
	}

	@RequestMapping(value = "/u-user", method = RequestMethod.GET)
	public String updateUserPage(HttpServletRequest request, Model model) {
		model.addAttribute("heading", "Update user");
		model.addAttribute("buttonAction", "Update user");
		User user = userService.findById(Integer.parseInt(request.getParameter("id")));
		model.addAttribute("user", user);
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "cu-user";
	}
	

}
