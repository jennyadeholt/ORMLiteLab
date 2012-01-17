package com.jayway.ormlite;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jayway.ormlite.model.Author;
import com.jayway.ormlite.model.Book;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "ormlitelab.db";
	private static final int DATABASE_VERSION = 3;
	private RuntimeExceptionDao<Author, Integer> authorDao = null;
	private RuntimeExceptionDao<Book, Integer> bookDao = null;

	/**
	 * @param context
	 *            The {@link Context}
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");

			TableUtils.createTable(connectionSource, Book.class);
			TableUtils.createTable(connectionSource, Author.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Book.class, true);
			TableUtils.dropTable(connectionSource, Author.class, true);

			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the {@link RuntimeExceptionDao} (Database Access Object) version
	 * of a {@link Dao} for the {@link Book} class. It will create it or just
	 * give the cached value. RuntimeExceptionDao only through
	 * RuntimeExceptions.
	 * 
	 * @return {@link RuntimeExceptionDao}
	 */
	public RuntimeExceptionDao<Book, Integer> getBookDao() {
		if (bookDao == null) {
			bookDao = getRuntimeExceptionDao(Book.class);
		}
		return bookDao;
	}

	/**
	 * Returns the {@link RuntimeExceptionDao} (Database Access Object) version
	 * of a {@link Dao} for the {@link Author} class. It will create it or just
	 * give the cached value. RuntimeExceptionDao only through
	 * RuntimeExceptions.
	 * 
	 * @return {@link RuntimeExceptionDao}
	 */
	public RuntimeExceptionDao<Author, Integer> getAuthorDao() {
		if (authorDao == null) {
			authorDao = getRuntimeExceptionDao(Author.class);
		}
		return authorDao;
	}

	@Override
	public void close() {
		super.close();
		authorDao = null;
		bookDao = null;
	}

	/**
	 * @param book
	 * @param author
	 */
	public void addBook(Book book, Author author) {
		List<Book> books = getBookDao().queryForMatching(book);
		book.setAuthor(author);

		if (books.isEmpty()) {
			book.setAuthor(author);
			bookDao.create(book);
		} else {
			book.setAuthor(author);
			bookDao.create(book);
			for (Book b : books) {
				b.setAuthor(author);
				bookDao.update(b);
			}
		}
	}

	/**
	 * @param author
	 */
	public void addAuthor(Author author) {
		getAuthorDao().createOrUpdate(author);
	}
}