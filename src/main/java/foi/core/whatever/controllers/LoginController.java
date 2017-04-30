package foi.core.whatever.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.User;
import foi.core.whatever.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();		
		User user = userService.findByUsername(username);
		if (user != null) {
			return "redirect:/smart-shopping";		
		}
		return "login";
    }
		
	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String loginError(Model model) {
		model.addAttribute("loginError", true);
	    return "login";
    }

}
