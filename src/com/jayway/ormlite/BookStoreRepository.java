package com.jayway.ormlite;

import java.util.List;

import com.jayway.ormlite.model.Author;
import com.jayway.ormlite.model.Book;

public class BookStoreRepository {
	
	private final DatabaseHelper helper;
	
	public BookStoreRepository(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public List<Author> getAuthors() {
		return helper.getAuthorDao().queryForAll();
	}
	
	public Author getAuthor(int id) {
		return helper.getAuthorDao().queryForId(id);
	}
		
	public void addAuthor(Author author) {
		helper.getAuthorDao().createIfNotExists(author);
	}
	
	public void addBook(Book book) {
		book.getAuthor().getBooks().add(book);
	}
	
	public void deleteBook(Book book) {
		book.getAuthor().getBooks().remove(book);
	}

}
