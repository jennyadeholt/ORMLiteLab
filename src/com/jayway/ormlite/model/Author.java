/**
 * 
 */
package com.jayway.ormlite.model;

import java.util.Collections;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author Jenny Nilsson, Jayway
 *
 */

public class Author {

	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField()
	private String name;
	
	@DatabaseField(canBeNull = true)
	private int birthyear;
	

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
	public int getBirthYear() {
		return birthyear;
	}

	/**
	 * @return The books
	 */
	public List<Book> getBooks() {
		//TODO: Implement for real
		return Collections.emptyList();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
