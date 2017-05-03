package foi.core.whatever.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.ReceiptProducts;

public interface ReceiptProductRepository extends JpaRepository<ReceiptProducts, Integer> {

	ReceiptProducts findById(int id);


}
