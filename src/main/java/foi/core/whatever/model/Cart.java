package foi.core.whatever.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name="cart_id")
	private int cartId;

	@ManyToOne
	private User user;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Product> products;

	@Column(name="active")
	private boolean active;

	public Cart() {
		super();
		setActive(true);
		products = new HashSet<>();
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


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getTotal() {
		double total=0;
		List<Product> prods = new ArrayList<>();
		prods.addAll(this.products);
		for(Product product : prods){
			total+=product.getPriceEUR();
		}
		return total;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

}
