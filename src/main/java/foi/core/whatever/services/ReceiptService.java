package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.Receipt;
import foi.core.whatever.model.User;

public interface ReceiptService {

	Receipt save(Receipt receipt);

	List<Receipt> findAll();

	Receipt findByReceiptId(int receiptId);

	List<Receipt> findByUser(User user);

}
