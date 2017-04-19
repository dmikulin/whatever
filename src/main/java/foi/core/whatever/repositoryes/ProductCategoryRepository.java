package foi.core.whatever.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

	ProductCategory findByCategoryId(int categoryId);

	ProductCategory findByName(String name);
	
	List<ProductCategory> findByActive(Boolean active);
	
}
