package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.Product;

public interface ProductService {
	
	Product save(Product product);

	Product findByProductId(int productId);

	Product findByName(String name);
	
	List<Product> findByActive(Boolean active);
	
	List<Product> findAll();

}
