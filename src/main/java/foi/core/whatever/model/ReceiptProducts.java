package foi.core.whatever.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "receipt_products")
public class ReceiptProducts {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name="id")
	private int id;

	@ManyToOne
	private Receipt receipt;

	@Column(name="price")
	private double price;

	@Column(name="product_id")
	private String productId;

	@Column(name="product_name")
	private String productName;

	@Column(name="quantity")
	private int quantity;

	public ReceiptProducts() {
		super();
	}

	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}






}
