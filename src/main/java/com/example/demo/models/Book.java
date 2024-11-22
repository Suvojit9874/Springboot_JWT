package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Book {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;

	    private String author;

    @Column(length = 500)
    private String description;

    public Book() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book(Long id, String title, String author, String isbn, String description) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
	}

    // Getters and Setters
}