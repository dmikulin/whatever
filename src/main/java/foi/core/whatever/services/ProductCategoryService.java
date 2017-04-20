package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.ProductCategory;

public interface ProductCategoryService {
	
	ProductCategory save(ProductCategory productCategory);

	ProductCategory findByCategoryId(int categoryId);

	ProductCategory findByName(String name);
	
	List<ProductCategory> findByActive(Boolean active);
	
	List<ProductCategory> findAll();

}
