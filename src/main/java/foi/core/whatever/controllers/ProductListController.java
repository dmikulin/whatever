package foi.core.whatever.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.YaaSServices;
import foi.core.whatever.model.Product;
import foi.core.whatever.services.ProductService;

@Controller
public class ProductListController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private YaaSServices yaaSServices;
	
	@RequestMapping(value = "/product-list", method = RequestMethod.GET)
	public String productListPage(HttpServletRequest request, Model model) throws ClientProtocolException, IOException, JSONException {

		//List<Product> products = productService.findAll();
		List<Product> products = yaaSServices.getAllProducts();
		model.addAttribute("products", products);

		return "product-list";	
	}
	
	@RequestMapping(value = "/admin/product-list", method = RequestMethod.GET)
	public String productListPageAdmin(HttpServletRequest request, Model model) throws ClientProtocolException, IOException, JSONException {
		return productListPage(request, model);
	}
	
}
