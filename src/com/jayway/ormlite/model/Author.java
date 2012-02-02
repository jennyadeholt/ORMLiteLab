/**
 * 
 */
package com.jayway.ormlite.model;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
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
	
	private ArrayList<Book> books;

	/**
	 * Only for OrmLite deserialization
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
	public List<Book> getBooks() {
		return books;
	}
}
