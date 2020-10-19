package pv826.entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Ramesh Fadatare
 *
 */
@Entity
@Table(name="users")
public class User
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(nullable=false)
	@NotBlank(message = "Name is mandatory")
	private String name;
	@Column(nullable=false, unique=true)
	@Email(message="{errors.invalid_email}")
	@NotBlank(message = "Email is mandatory")
	private String email;
	@Column(nullable=false)
	@Size(min=4)
	@NotBlank(message = "Email is mandatory")
	private String password;

	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="user_role",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DbImage> images;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = false)
	private ContactInfo contactInfo;

	public void setContactInfo(ContactInfo contactInfo) {
		if (contactInfo == null) {
			if (this.contactInfo != null) {
				this.contactInfo.setUser(null);
			}
		}
		else {
			contactInfo.setUser(this);
		}
		this.contactInfo = contactInfo;
	}

	public List<Order> getOrders() {
		return orders;
	}



	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}


	public User() {
		roles = new ArrayList<Role>();
		images = new ArrayList<DbImage>();
	}

	public User(@NotEmpty() String name, @NotEmpty @Email(message = "{errors.invalid_email}") String email, @NotEmpty @Size(min = 4) String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		roles = new ArrayList<Role>();
		images = new ArrayList<DbImage>();
	}

	public void addImage(DbImage image) {
		image.setUser(this);
		images.add(image);
	}

	public void removeImage(DbImage image) {
		images.remove(image);
	}

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public List<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public List<DbImage> getImages() {
		return images;
	}

	public void setImages(List<DbImage> images) {
		this.images = images;
	}
}
