package foi.core.whatever.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class Receipt {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name="receipt_id")
	private int receiptId;

	@ManyToOne
	private User user;

	@Column(name="document_date")
	private LocalDateTime documentDate;

	@Column(name="total")
	private double total;

	@OneToMany(mappedBy = "receipt")
	private List<ReceiptProducts> receiptProducts;

	@Column(name="active")
	private boolean active;

	public Receipt() {
		super();
		setActive(true);
		receiptProducts = new ArrayList<ReceiptProducts>();
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public LocalDateTime getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(LocalDateTime documentDate) {
		this.documentDate = documentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ReceiptProducts> getReceiptProducts() {
		return receiptProducts;
	}

	public void setReceiptProducts(List<ReceiptProducts> receiptProducts) {
		this.receiptProducts = receiptProducts;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTimeString(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		return dateTime.format(formatter);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}



}
