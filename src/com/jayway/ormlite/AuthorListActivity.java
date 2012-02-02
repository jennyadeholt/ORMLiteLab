package com.jayway.ormlite;

import java.util.List;

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
import com.jayway.ormlite.model.Author;
import com.jayway.ormlite.model.Book;

/**
 * @author Jenny Nilsson, Jayway
 * 
 */
public class AuthorListActivity extends OrmLiteBaseListActivity<DatabaseHelper> {

	private BookStoreRepository repo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author_list_view);

		repo = new BookStoreRepository(getHelper());
		addDatabaseContent();

		Log.d("", "Items in Author database "
				+ getHelper().getAuthorDao().queryForAll().size());
		Log.d("", "Items in Book database "
				+ getHelper().getBookDao().queryForAll().size());

		updateListAdapter();

		Button b = ((Button) findViewById(R.id.add_author));
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.new_author);
				if (!TextUtils.isEmpty(text.getText())) {
					repo.addAuthor(new Author(text.getText().toString()));
					text.setText("");
					updateListAdapter();
				}
			}
		});
	}

	private void updateListAdapter() {
		setListAdapter(new ArrayAdapter<Author>(AuthorListActivity.this,
				android.R.layout.simple_list_item_1, repo.getAuthors()));
	}

	private void addDatabaseContent() {

		if (getHelper().getAuthorDao().queryForAll().isEmpty()) {
			Author author;

			author = new Author("Selma Lagerlöv");
			repo.addAuthor(author);
			repo.addBook(new Book("Kejsaren av Portugalien", author));

			author = new Author("Stephen King");
			repo.addAuthor(author);
			repo.addBook(new Book("Pestens tid", author));

			author = new Author("Astrid Lindgren");
			repo.addAuthor(author);
			repo.addBook(new Book("Pippi Långstrump", author));

			author = new Author("Johanna Nilsson");
			repo.addAuthor(author);
			repo.addBook(new Book("Hon går genom tavlan ut ur bilden", author));

			author = new Author("Harry Martinsson");
			repo.addAuthor(author);
			repo.addBook(new Book("Aniara", author));
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Author author = (Author) l.getItemAtPosition(position);

		List<Book> books = author.getBooks();

		Toast.makeText(this, "Books : " + books.size(), Toast.LENGTH_SHORT)
				.show();

		Intent bookIntent = new Intent(getApplicationContext(), BookListActivity.class);
		bookIntent.putExtra("id", author.getId());
		startActivity(bookIntent);
	}
}
