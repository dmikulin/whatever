package ecx.mpopijac.restaurants.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "id", unique = true)
	private int id;
	@Column(name = "messageContent", columnDefinition = "TEXT")
	private String messageContent;
	@Column(name = "approved")
	private boolean approved;
	@Column(name = "hash")
	private String hash;
	@ManyToOne
	private User author;
	@ManyToOne
	private Article article;

	public Comment() {
	}

	public Comment(String messageContent, User author, Article article) {
		super();
		this.setMessageContentAsHtml(messageContent);
		this.approved = false;
		ShaPasswordEncoder sha = new ShaPasswordEncoder(512);
		this.hash = sha.encodePassword(messageContent, author);
		this.author = author;
		this.article = article;
	}
	
	public Comment(String messageContent, boolean approved, User author, Article article) {
		super();
		this.setMessageContentAsHtml(messageContent);
		this.approved = approved;
		this.author = author;
		this.article = article;
		ShaPasswordEncoder sha = new ShaPasswordEncoder(512);
		this.hash = sha.encodePassword(messageContent, author);
	}

	public Comment(int id, String messageContent, boolean approved, User author, Article article) {
		super();
		this.id = id;
		this.setMessageContentAsHtml(messageContent);
		this.approved = approved;
		this.author = author;
		this.article = article;
		ShaPasswordEncoder sha = new ShaPasswordEncoder(512);
		this.hash = sha.encodePassword(messageContent, author);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 *Return plain/text messageContent
	 * @return String
	 */
	public String getMessageContentAsPlainText() {
		return messageContent.replace("<br/>", "\n");
	}
	
	/**
	 * Return messageContent with html tags
	 * @return String
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * Set messageContent as-is
	 * @param messageContent
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	/**
	 * New line will be converted in html tag <br/>
	 * @param messageContent
	 */
	public void setMessageContentAsHtml(String messageContent) {
		this.messageContent = messageContent.replace("\n", "<br/>");
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((messageContent == null) ? 0 : messageContent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (approved != other.approved)
			return false;
		if (id != other.id)
			return false;
		if (messageContent == null) {
			if (other.messageContent != null)
				return false;
		} else if (!messageContent.equals(other.messageContent))
			return false;
		return true;
	}

}
