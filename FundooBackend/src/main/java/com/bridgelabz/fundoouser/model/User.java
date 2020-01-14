package com.bridgelabz.fundoouser.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :User Model Class
 *
 *********************************************************************************************************/

@Component
@Entity
@Table(name = "userDetails")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private long phonenumber;
	@NotNull
	private boolean validate;

	public User() {

	}

	// User Constructor
	public User(int id, @NotNull String firstname, @NotNull String lastname, @NotNull String email,
			@NotNull String password, @NotNull long phonenumber, @NotNull boolean validate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
		this.validate = validate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", phonenumber=" + phonenumber + ", validate=" + validate + "]";
	}
}
