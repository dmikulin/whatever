package foi.core.whatever.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.lowagie.text.pdf.codec.Base64;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "user_id")
	private int userId;
	@Column(name = "yaas_id")
	private String yaasId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();

	@Lob
	@Column(name = "avatar", columnDefinition = "mediumblob")
	private byte[] avatar;

	@Column(name = "active")
	private boolean active;

	public User() {
		super();
		setActive(true);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getYaasId() {
		return yaasId;
	}

	public void setYaasId(String yaasId) {
		this.yaasId = yaasId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRoles(Role role) {
		this.roles.add(role);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRolesString(List<Role> roles) {
		String rolesString = "";
		for (Role role : roles) {
			rolesString += role.getRoleName() + " ";
		}
		return rolesString;
	}

	public boolean hasAdminRole(List<Role> roles) {
		for (Role role : roles) {
			if (role.getRoleName().equals("Admin")){
				return true;
			}
		}
		return false;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String encodeBase64(byte[] picture) {
		return "data:image/png;base64," + Base64.encodeBytes(picture);
	}
}
