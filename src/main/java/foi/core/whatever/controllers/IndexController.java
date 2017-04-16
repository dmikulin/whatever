package foi.core.whatever.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String indexPage(Model model) {
		return "redirect:/product-list";	
	}

}
