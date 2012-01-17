package com.jayway.ormlite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.j256.ormlite.dao.ForeignCollection;
import com.jayway.ormlite.model.Author;
import com.jayway.ormlite.model.Book;

/**
 * @author Jenny Nilsson, Jayway
 * 
 */
public class AuthorList extends OrmLiteBaseListActivity<DatabaseHelper> {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author_list_view);

		addDatabaseContent();

		Log.d("", "Items in Author database " + getHelper().getAuthorDao().queryForAll().size());
		Log.d("", "Items in Book database " + getHelper().getBookDao().queryForAll().size());

		updateListAdapter();

		Button b = ((Button) findViewById(R.id.add_author));
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.new_author);
				if (!TextUtils.isEmpty(text.getText())) {
					getHelper().getAuthorDao().createIfNotExists(new Author(text.getText().toString()));
					text.setText("");
					updateListAdapter();
				}
			}
		});
	}


	private void updateListAdapter() {
		setListAdapter(new ArrayAdapter<Author>(AuthorList.this, android.R.layout.simple_list_item_1, getHelper().getAuthorDao().queryForAll()));
	}

	private void addDatabaseContent() {

		if (getHelper().getAuthorDao().queryForAll().isEmpty()) {
			Author selma = new Author("Selma Lagerlöv");
			getHelper().addAuthor(selma);
			Book book = new Book("Kejsaren av Portugalien");
			getHelper().addBook(book, selma);

			Author stephen = new Author("Stephen King");
			getHelper().addAuthor(stephen);
			book = new Book("Pestens tid");
			getHelper().addBook(book, stephen);

			Author astrid = new Author("Astrid Lindgren");
			getHelper().addAuthor(astrid);
			book = new Book("Pippi Långstrump");
			getHelper().addBook(book, astrid);

			Author johanna = new Author("Johanna Nilsson");
			getHelper().addAuthor(johanna);
			book = new Book("Hon går genom tavlan ut ur bilden");
			getHelper().addBook(book, johanna);

			Author harry = new Author("Harry Martinsson");
			getHelper().addAuthor(harry);
			book = new Book("Aniara");
			getHelper().addBook(book, harry);
		}
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Author author = (Author) l.getItemAtPosition(position);

		ForeignCollection<Book> books = author.getBooks();

		Toast.makeText(this, "Books : " + books.size(), Toast.LENGTH_SHORT).show();

		Intent bookIntent = new Intent(getApplicationContext(), BookList.class);
		bookIntent.putExtra("id", author.getName());
		startActivity(bookIntent);
	}
}
