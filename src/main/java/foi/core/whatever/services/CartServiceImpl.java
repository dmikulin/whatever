package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.User;
import foi.core.whatever.repositoryes.CartRepository;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public Cart findByCartId(int cartId) {
		return cartRepository.findByCartId(cartId);
	}

	@Override
	public Cart findByUser(User user) {
		return cartRepository.findByUser(user);
	}

	
}
