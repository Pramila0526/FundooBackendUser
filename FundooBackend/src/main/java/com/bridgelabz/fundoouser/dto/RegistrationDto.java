package com.bridgelabz.fundoouser.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Data For Registration
 *
 *********************************************************************************************************/

public class RegistrationDto {
	@NotNull
	private String firstname;
	
	@NotNull(message = "Last Name is mandatory")	
	private String lastname;
	
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="phone format wrong" )
	private String email;

	@NotNull(message = "password is mandatory")
	private String password;
	
	@NotNull
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message="phone format wrong" )
	private String phonenumber;

	public RegistrationDto()
	{
		
	}
	
	public RegistrationDto(
			@NotNull(message = "First Name is mandatory") @Pattern(regexp = "^[a-zA-Z]*$") String firstname,
			@NotNull(message = "Last Name is mandatory") @Pattern(regexp = "^[a-zA-Z]*$") String lastname,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "phone format wrong") String email,
			@NotNull(message = "password is mandatory") String password,
			@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "phone format wrong") @NotBlank(message = "phone is mandatory") String phonenumber) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String toString() {
		return "RegistrationDto [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", phonenumber=" + phonenumber + "]";
	}
}