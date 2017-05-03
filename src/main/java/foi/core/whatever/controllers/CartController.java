package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.YaaSServices;
import foi.core.whatever.model.Cart;
import foi.core.whatever.model.CartItems;
import foi.core.whatever.model.User;
import foi.core.whatever.services.CartService;
import foi.core.whatever.services.UserService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private YaaSServices yaasServices;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String productListPage(HttpServletRequest request, Model model) throws Exception{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		Cart cart = cartService.findByUser(user);

		List<CartItems> items = yaasServices.getItemsFromCart(cart.getYaasId());
		double total=0;
		if(items!=null){
			for(CartItems item : items){
				total+=item.getPrice()*item.getQuantity();
			}
		}		

		model.addAttribute("items", items);
		model.addAttribute("total", total);

		return "cart";	
	}

	@RequestMapping(value = "/admin/cart", method = RequestMethod.GET)
	public String productListPageAdmin(HttpServletRequest request, Model model) throws Exception{
		return productListPage(request, model);
	}

}
