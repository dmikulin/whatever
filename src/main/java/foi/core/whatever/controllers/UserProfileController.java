package foi.core.whatever.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.YaaSServices;
import foi.core.whatever.model.User;
import foi.core.whatever.services.UserService;

@Controller
public class UserProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private YaaSServices yaaSServices;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/user-profile", method = RequestMethod.GET)
	public String yourProfileGet(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);

		return "user-profile";
	}

	@RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
	public String editProfile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());

		boolean usernameChanged = false;
		List<String> errors = new ArrayList<>();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String telephone = request.getParameter("phone");
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		if (!username.equals(user.getUsername())) {
			usernameChanged = true;
			User userExist = userService.findByUsername(username);
			if (userExist != null) {
				errors.add("error1");
			}
		}
		if (!email.equals(user.getEmail())) {
			User userExist = userService.findByEmail(email);
			if (userExist != null) {
				errors.add("error2");
			}
		}

		if (!errors.isEmpty()) {
			model.addAttribute("errors", errors);
			return yourProfileGet(model);
		}

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhone(telephone);
		user.setEmail(email);
		user.setUsername(username);
		userService.save(user);

		yaaSServices.editCustomer(user);

		if (usernameChanged) {
			if (auth != null){    
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/login";
		}
		return yourProfileGet(model);
	}

	@RequestMapping(value = "/change-pass", method = RequestMethod.POST)
	public String changePass(HttpServletRequest request, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());

		String oldPassword = request.getParameter("old-password");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		List<String> errors = new ArrayList<>();
		if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			if (password.equals(confirmPassword)) {
				user.setPassword(bCryptPasswordEncoder.encode(password));
				userService.save(user);
			} else {
				errors.add("error3");
			}
		} else {
			errors.add("error4");
		}

		if (!errors.isEmpty()) {
			model.addAttribute("errors", errors);
		}
		return yourProfileGet(model);
	}

}
