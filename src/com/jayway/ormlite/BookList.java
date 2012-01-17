package com.jayway.ormlite;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.jayway.ormlite.model.Author;
import com.jayway.ormlite.model.Book;

/**
 * @author Jenny Nilsson, Jayway
 * 
 */
public class BookList extends OrmLiteBaseListActivity<DatabaseHelper> {

	private static final int DELETE_BOOK_DIALOG = 0;
	private Author author;
	private Book bookToDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.book_list_view);

		String id = (String) getIntent().getExtras().get("id");
		final List<Author> authors = getHelper().getAuthorDao().queryForEq("name", id);
		author = authors.size() >= 1 ? authors.get(0) : new Author("Unknown");
		updateListAdapter();

		((TextView) findViewById(R.id.author)).setText(author.getName());

		((Button) findViewById(R.id.add_book)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.new_book);

				if (!TextUtils.isEmpty(text.getText())) {
					Book book = new Book(text.getText().toString());
					getHelper().addBook(book, author);
					text.setText("");
					updateListAdapter();
				}
			}
		});
	}

	private void updateListAdapter() {
		setListAdapter(new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, getHelper().getBookDao().queryForEq("author_id", author)));
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DELETE_BOOK_DIALOG:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to delete?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					getHelper().getBookDao().delete(bookToDelete);
					updateListAdapter();
				}
			}).setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			return builder.create();
		default:
			return super.onCreateDialog(id);
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		bookToDelete = ((Book) l.getItemAtPosition(position));
		showDialog(DELETE_BOOK_DIALOG);
	}
}
