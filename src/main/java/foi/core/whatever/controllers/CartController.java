package foi.core.whatever.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.Product;
import foi.core.whatever.model.User;
import foi.core.whatever.services.CartService;
import foi.core.whatever.services.UserService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String productListPage(HttpServletRequest request, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		Cart cart = cartService.findByUser(user);

		List<Product> products = new ArrayList<>();
		products.addAll(cart.getProducts());
		model.addAttribute("products", products);
		model.addAttribute("total", cart.getTotal());

		return "cart";	
	}

	@RequestMapping(value = "/admin/cart", method = RequestMethod.GET)
	public String productListPageAdmin(HttpServletRequest request, Model model) {
		return productListPage(request, model);
	}

}
