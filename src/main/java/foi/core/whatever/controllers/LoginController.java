package foi.core.whatever.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			System.out.println("NE DELA!!");
		}
        return "login";
    }
		
	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String loginError(Model model) {
		model.addAttribute("loginError", true);
	    return "login";
    }

}
