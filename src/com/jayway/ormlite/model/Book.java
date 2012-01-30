package com.jayway.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Jenny Nilsson, Jayway
 * 
 */
/**
 * @author Jenny Nilsson, Jayway
 * 
 */
@DatabaseTable(tableName = "book")
public class Book {

	@DatabaseField(foreign = true, canBeNull = true)
	private Author author;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String title;

	/**
	 * Used only for OrmLite
	 */
	@SuppressWarnings("unused")
	private Book() {

	}

	/**
	 * @param title
	 * @param author 
	 */
	public Book(String title, Author author) {
		this.title = title;
		this.author = author;
	}

	/**
	 * @param author
	 */
	public void setAuthor(Author author) {
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

	/**
	 * @return The id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return title;
	}
}
