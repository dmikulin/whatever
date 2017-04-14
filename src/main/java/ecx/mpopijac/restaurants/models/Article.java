package ecx.mpopijac.restaurants.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "id", unique = true)
	private int id;
	@Column(name = "headline")
	private String headline;
	@Column(name = "imageLocation")
	private String imageLocation;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	@ManyToOne
	private Restaurant restaurant;
	@ManyToOne
	private User author;
	@OneToMany(mappedBy = "article")
	private List<Comment> comments = new ArrayList<Comment>();

	public Article() {
		this.description="";
	}
	
	public Article( String headline, String imageLocation, String description, Restaurant restaurant,
			User author) {
		super();
		this.headline = headline;
		this.imageLocation = imageLocation;
		this.description = description;
		this.restaurant = restaurant;
		this.author = author;
	}

	public Article(int id, String headline, String imageLocation, String description, Restaurant restaurant,
			User author, ArrayList<Comment> comments) {
		super();
		this.id = id;
		this.headline = headline;
		this.imageLocation = imageLocation;
		this.description = description;
		this.restaurant = restaurant;
		this.author = author;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String image) {
		this.imageLocation = image;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

}
