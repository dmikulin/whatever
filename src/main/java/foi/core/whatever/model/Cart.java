package foi.core.whatever.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name="cart_id")
	private int cartId;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private List<Product> products = new ArrayList<Product>();
	
	@Column(name="active")
	private boolean active;

	public Cart() {
		super();
		setActive(true);
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
