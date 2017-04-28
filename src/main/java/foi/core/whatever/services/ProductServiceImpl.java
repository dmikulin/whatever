package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.Product;
import foi.core.whatever.repositoryes.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findByProductId(int productId) {
		return productRepository.findByProductId(productId);
	}

	@Override
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> findByActive(Boolean active) {
		return productRepository.findByActive(active);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findByProductNumber(String productNumber) {
		return productRepository.findByProductNumber(productNumber);
	}

}
