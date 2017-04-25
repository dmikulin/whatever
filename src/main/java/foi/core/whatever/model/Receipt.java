package foi.core.whatever.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	private LocalDate documentDate;
	
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
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


	public LocalDate getDocumentDate() {
		return documentDate;
	}


	public void setDocumentDate(LocalDate documentDate) {
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


}
