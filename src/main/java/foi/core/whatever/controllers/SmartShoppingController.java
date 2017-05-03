package foi.core.whatever.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import foi.core.whatever.model.Receipt;
import foi.core.whatever.model.ReceiptProducts;
import foi.core.whatever.model.User;
import foi.core.whatever.services.CartService;
import foi.core.whatever.services.ReceiptService;
import foi.core.whatever.services.UserService;

@Controller
public class SmartShoppingController {

	@Autowired
	private YaaSServices yaasServices;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReceiptService receiptService;

	@RequestMapping(value = "/smart-shopping", method = RequestMethod.GET)
	public String getSmartShop(Model model) {
		return "smart-shopping";	
	}

	@RequestMapping(value = "/smart-shopping-buy", method = RequestMethod.GET)
	public String payCart(Model model) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		Cart cart = cartService.findByUser(user);

		Receipt receipt = new Receipt();
		receipt.setDocumentDate(LocalDateTime.now());
		receipt.setActive(true);
		receipt.setUser(user);

		List<ReceiptProducts> products = new ArrayList<>(); 
		List<CartItems> items = yaasServices.getItemsFromCart(cart.getYaasId());
		double total=0;
		if(items!=null){
			for(CartItems item : items){
				ReceiptProducts product = new ReceiptProducts(); 
				product.setPrice(item.getPrice());
				product.setProductId(item.getProductId());
				product.setProductName(item.getProductName());
				product.setQuantity(item.getQuantity());
				products.add(product);
				total+=item.getPrice()*item.getQuantity();
			}
			receipt.setReceiptProducts(products);
			receipt.setTotal(total);
			yaasServices.deleteItemsFromCart(cart.getYaasId());
		}
		receiptService.save(receipt);

		return "redirect:/receipt-list";	
	}

}
