package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import foi.core.whatever.model.ProductCategory;
import foi.core.whatever.services.ProductCategoryService;

@RestController
@RequestMapping("/api")
public class RestApiController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<ProductCategory>> getCategories(HttpServletRequest request) {
		try {
			final List<ProductCategory> productCategories = productCategoryService.findAll();
			return new ResponseEntity<List<ProductCategory>>(productCategories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/newCategory", method = RequestMethod.GET)
	public ResponseEntity<ProductCategory> newCategory(HttpServletRequest request) {
		try {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setName(request.getParameter("categoryName"));
			productCategory.setDescription(request.getParameter("categoryDesc"));
			productCategoryService.save(productCategory);
			return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
