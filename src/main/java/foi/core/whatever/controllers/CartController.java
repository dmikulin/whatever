package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Product;
import foi.core.whatever.services.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String productListPage(HttpServletRequest request, Model model) {

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		return "cart";	
	}
	
	@RequestMapping(value = "/admin/cart", method = RequestMethod.GET)
	public String productListPageAdmin(HttpServletRequest request, Model model) {
		return productListPage(request, model);
	}
	
}
