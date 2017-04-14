package ecx.mpopijac.restaurants.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants")
public class Restaurant {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "id", unique = true)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public Restaurant() {
	}
	
	public Restaurant(String name, String address, String description) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
	}
	
	public Restaurant(int id, String name, String address, String description) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Return plan/text description
	 * @return String
	 */
	public String getDescriptionAsPlainText() {
		return description.replace("<br/>", "\n");
	}
	
	/**
	 * Return decription with html tags
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set description as-is
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * New line will be converted in html tag <br/>
	 * @param description
	 */
	public void setDescriptionAsHtml(String description) {
		this.description = description.replace("\n", "<br/>");
	}

}
