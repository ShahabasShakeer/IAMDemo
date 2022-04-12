package com.shahabas.iamdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.shahabas.iamdemo.annotations.IndianPhone;

@Entity
@Table(name="contact")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@NotEmpty(message="Enter the phone number please")
	@IndianPhone(value="+56", message="Jupiter numbers starts with +56")
	@Pattern(regexp="\\+\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d", message="Enter the phone number in correct format.")
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="contact_id")
	private List<Address> addresses;
	
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void addAddress(Address address) {
		if (addresses == null) {
			addresses = new ArrayList<>();
		}
		addresses.add(address);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Contact() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contact [email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contact(String email,
			@NotEmpty(message = "Enter the phone number please") @Pattern(regexp = "\\+\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d", message = "Enter the phone number in correct format.") String phoneNumber) {
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

}
