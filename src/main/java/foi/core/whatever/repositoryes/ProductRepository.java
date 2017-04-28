package foi.core.whatever.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByProductId(int productId);

	Product findByName(String name);
	
	Product findByProductNumber(String productNumber);
	
	List<Product> findByActive(Boolean active);
	
}
