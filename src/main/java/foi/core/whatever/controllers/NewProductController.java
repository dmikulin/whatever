package foi.core.whatever.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Product;
import foi.core.whatever.services.ProductService;

@Controller
@RequestMapping("/admin")
public class NewProductController {
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/new-product", method = RequestMethod.GET)
	public String signUpUser(Model model) {
		return "new-product";
	}

	@RequestMapping(value = "/new-product", method = RequestMethod.POST)
	public String SignUpUser(HttpServletRequest request, Model model) {
		
		Product product = new Product();
		product.setProductNumber(request.getParameter("product_no"));
		product.setName(request.getParameter("product_name"));
		product.setDescription(request.getParameter("description"));
		product.setPriceEUR( Double.parseDouble(request.getParameter("price_EUR")));
		product.setPriceUSD( Double.parseDouble(request.getParameter("price_USD")));
		productService.save(product);
		
		return "redirect:product-list";
	}

}
