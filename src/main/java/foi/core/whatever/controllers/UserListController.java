package foi.core.whatever.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.YaaSServices;
import foi.core.whatever.model.User;
import foi.core.whatever.services.UserService;

@Controller
@RequestMapping("/admin")
public class UserListController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private YaaSServices yaaSServices;
	
	@RequestMapping(value = "/user-list", method = RequestMethod.GET)
	public String userListPage(HttpServletRequest request, Model model) throws ClientProtocolException, IOException, JSONException {
		
		String active = request.getParameter("active");
		if(active == null){
			active = "1";
		}
		List<User> allUsers = yaaSServices.getAllUsers();
		
		for(User user: allUsers){
			user.setAvatar(userService.findByEmail(user.getEmail()).getAvatar());
			user.setUserId(userService.findByEmail(user.getEmail()).getUserId());
		}
		
		//List<User> activeUsers = userService.findByActive(true);
		//List<User> deactiveUsers = userService.findByActive(false);
		//model.addAttribute("activeUsers", activeUsers);
		//model.addAttribute("deactiveUsers", deactiveUsers);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("active", Integer.parseInt(active));
		
		return "user-list";	
	}
	
	@RequestMapping(value = "/deactivate-user/{id}", method = RequestMethod.GET)
	public String deactivateUser(HttpServletRequest request, Model model, @PathVariable("id") int userId) throws ClientProtocolException, IOException {
		
		User user = userService.findByUserId(userId);
		user.setActive(false);
		userService.save(user);
		
		yaaSServices.editCustomer(user);
		
		return "redirect:/admin/user-list/?active=2";
	}
	
	@RequestMapping(value = "/activate-user/{id}", method = RequestMethod.GET)
	public String activateUser(HttpServletRequest request, Model model, @PathVariable("id") int userId) throws ClientProtocolException, IOException {
		
		User user = userService.findByUserId(userId);
		user.setActive(true);
		userService.save(user);
		
		yaaSServices.editCustomer(user);
		
		return "redirect:/admin/user-list/?active=1";
	}
}
