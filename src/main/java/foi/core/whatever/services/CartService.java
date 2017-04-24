package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.User;

public interface CartService {

	Cart save(Cart cart);

	List<Cart> findAll();
	
	Cart findByCartId(int cartId);
	
	Cart findByUser(User user);

}
