package foi.core.whatever.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class NewProductController {


	@RequestMapping(value = "/new-product", method = RequestMethod.GET)
	public String signUpUser(Model model) {

		return "new-product";
	}

	@RequestMapping(value = "/new-product", method = RequestMethod.POST)
	public String SignUpUser(HttpServletRequest request, Model model) {

		return "redirect:product-list";
	}

}
