package foi.core.whatever.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product")
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

	@Column(name="active")
	private boolean active;

	public Product() {
		super();
		setActive(true);
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



}
