package com.shahabas.iamdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="enabled")
	private int enabled;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", nullable=false)
	private List<Quote> quotes;
	
	/*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;
    */
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private List<Authority> authorities;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", enabled=" + enabled + ", quotes=" + quotes + "]";
	}

	public User(String username, int enabled, String password) {
		this.username = username;
		this.enabled = enabled;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getEnabled() {
		return enabled;
	}

	public void addQuote(Quote quote) {
		if (quotes == null) {
			quotes = new ArrayList<>();
		}
		quotes.add(quote);
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
