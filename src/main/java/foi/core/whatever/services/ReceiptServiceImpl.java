package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.Receipt;
import foi.core.whatever.model.User;
import foi.core.whatever.repositoryes.ReceiptRepository;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;

	@Override
	public Receipt save(Receipt receipt) {
		return receiptRepository.save(receipt);
	}

	@Override
	public List<Receipt> findAll() {
		return receiptRepository.findAll();
	}

	@Override
	public Receipt findByReceiptId(int receiptId) {
		return receiptRepository.findByReceiptId(receiptId);
	}

	@Override
	public List<Receipt> findByUser(User user) {
		return receiptRepository.findByUser(user);
	}




}
