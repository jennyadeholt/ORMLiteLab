/**
 * 
 */
package com.jayway.ormlite.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Jenny Nilsson, Jayway
 *
 */

@DatabaseTable(tableName = "author")
public class Author {

	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField()
	private String name;
	
	@DatabaseField(canBeNull = true)
	private int birthday;
	
	@ForeignCollectionField
	private ForeignCollection<Book> books;

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Author() {

	}

	/**
	 * @param name
	 */
	public Author(String name){
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The birtday
	 */
	public int getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return name;

	}

	/**
	 * @return The books
	 */
	public ForeignCollection<Book> getBooks() {
		return books;
	}
}
