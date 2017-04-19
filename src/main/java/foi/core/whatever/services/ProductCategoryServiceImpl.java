package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.ProductCategory;
import foi.core.whatever.repositoryes.ProductCategoryRepository;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public ProductCategory save(ProductCategory product) {
		return productCategoryRepository.save(product);
	}

	@Override
	public ProductCategory findByCategoryId(int categoryId) {
		return productCategoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public ProductCategory findByName(String name) {
		return productCategoryRepository.findByName(name);
	}

	@Override
	public List<ProductCategory> findByActive(Boolean active) {
		return productCategoryRepository.findByActive(active);
	}

	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	
}
