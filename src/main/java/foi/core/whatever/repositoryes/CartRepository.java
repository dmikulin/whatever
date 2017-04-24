package foi.core.whatever.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByCartId(int cartId);
	
	Cart findByUser(User user);

}
