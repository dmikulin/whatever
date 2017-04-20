package foi.core.whatever.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Product;
import foi.core.whatever.model.ProductCategory;
import foi.core.whatever.services.ProductCategoryService;
import foi.core.whatever.services.ProductService;

@Controller
@RequestMapping("/admin")
public class NewProductController {
	
	@Autowired
	ProductService productService;	
	
	@Autowired
	ProductCategoryService productCategoryService;

	@RequestMapping(value = "/new-product", method = RequestMethod.GET)
	public String signUpUser(Model model) {
		
		List<ProductCategory> categories = productCategoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "new-product";
	}

	@RequestMapping(value = "/new-product", method = RequestMethod.POST)
	public String SignUpUser(HttpServletRequest request, Model model) throws ClientProtocolException, IOException {
		
		ProductCategory category = productCategoryService.findByCategoryId(Integer.parseInt(request.getParameter("productCategory")));
		
		Product product = new Product();
		product.setProductNumber(request.getParameter("product_no"));
		product.setName(request.getParameter("product_name"));
		product.setCategory(category);
		product.setDescription(request.getParameter("description"));
		product.setPriceEUR( Double.parseDouble(request.getParameter("price_EUR")));
		product.setPriceUSD( Double.parseDouble(request.getParameter("price_USD")));
		productService.save(product);
		
		String url = "https://api.yaas.io/hybris/product/v2/milena/products";
		String token = "Bearer 022-c1169181-7cdd-4da1-936e-9d38797f6cb8";
		
		String json = "{\"name\":\""+product.getName()+"\",";
		       json += "\"code\":\""+product.getProductNumber()+"\",";
		       json += "\"description\":\""+product.getDescription()+"\"}";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		
		post.setHeader("Authorization", token);
		post.setHeader("Content-Language", "en");
		post.setHeader("Content-Type", "application/json");
		
        HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		
		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());
		
		return "redirect:product-list";
	}

}
