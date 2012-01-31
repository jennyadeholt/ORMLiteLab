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
	
	public List<Book> getBooks() {
		return helper.getBookDao().queryForAll();
	}
	
	public void addBook(Book book) {
		helper.getBookDao().createIfNotExists(book);
	}
	
	public void deleteBook(Book book) {
		helper.getBookDao().delete(book);
	}

}
