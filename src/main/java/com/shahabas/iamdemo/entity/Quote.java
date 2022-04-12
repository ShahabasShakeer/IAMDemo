package com.shahabas.iamdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quote")
public class Quote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="approved")
	private int approved;
	
	@Column(name="content")
	private String content;
	
	public Quote() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Quote(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", content=" + content + "]";
	}

}
