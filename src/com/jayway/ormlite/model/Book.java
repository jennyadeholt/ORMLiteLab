package com.jayway.ormlite.model;


/**
 * @author Jenny Nilsson, Jayway
 * 
 */
public class Book {

	// This is a reference to the author, so it's a foreign field for the database
	private Author author;

	private String title;

	/**
	 * @param title
	 * @param author 
	 */
	public Book(String title, Author author) {
		this.title = title;
		this.author = author;
	}

	/**
	 * @return The author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title;
	}
}
