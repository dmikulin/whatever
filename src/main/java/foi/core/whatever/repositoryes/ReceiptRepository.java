package foi.core.whatever.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.Receipt;
import foi.core.whatever.model.User;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

	Receipt findByReceiptId(int receiptId);

	List<Receipt> findByUser(User user);

}
