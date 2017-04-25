package foi.core.whatever.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name="product_id")
	private int productId;
	@Column(name="product_number")
	private String productNumber;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="price_EUR")
	private Double priceEUR;
	@Column(name="price_USD")
	private Double priceUSD;
    
    @OneToMany(mappedBy = "product")
    private List<CartProducts> cartProducts;
    
    @OneToMany(mappedBy = "product")
    private List<ReceiptProducts> receiptProducts;
	
	@ManyToOne
	private ProductCategory category;
	
	@Column(name="active")
	private boolean active;

	public Product() {
		super();
		setActive(true);
		cartProducts = new ArrayList<CartProducts>();
		receiptProducts = new ArrayList<ReceiptProducts>();
	}

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductNumber() {
		return productNumber;
	}


	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPriceEUR() {
		return priceEUR;
	}

	public void setPriceEUR(Double priceEUR) {
		this.priceEUR = priceEUR;
	}

	public Double getPriceUSD() {
		return priceUSD;
	}

	public void setPriceUSD(Double priceUSD) {
		this.priceUSD = priceUSD;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	public List<CartProducts> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProducts> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public List<ReceiptProducts> getReceiptProducts() {
		return receiptProducts;
	}

	public void setReceiptProducts(List<ReceiptProducts> receiptProducts) {
		this.receiptProducts = receiptProducts;
	}


}
