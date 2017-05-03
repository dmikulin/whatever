package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.ReceiptProducts;

public interface ReceiptProductService {

	ReceiptProducts save(ReceiptProducts receiptProducts);

	List<ReceiptProducts> findAll();

	ReceiptProducts findById(int id);


}
