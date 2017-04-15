package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.User;
import foi.core.whatever.services.UserService;

@Controller
public class UserListController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user-list", method = RequestMethod.GET)
	public String userListPage(HttpServletRequest request, Model model) {

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		return "user-list";	
	}
	
}
