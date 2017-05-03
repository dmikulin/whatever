package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.ReceiptProducts;
import foi.core.whatever.repositoryes.ReceiptProductRepository;

@Service("receiptProductService")
public class ReceiptProductServiceImpl implements ReceiptProductService {

	@Autowired
	private ReceiptProductRepository receiptProductRepository;

	@Override
	public ReceiptProducts save(ReceiptProducts receiptProducts) {
		return receiptProductRepository.save(receiptProducts);
	}

	@Override
	public List<ReceiptProducts> findAll() {
		return receiptProductRepository.findAll();
	}

	@Override
	public ReceiptProducts findById(int id) {
		return receiptProductRepository.findById(id);
	}

}
