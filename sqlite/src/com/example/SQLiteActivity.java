package com.example;

import com.example.db.DatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SQLiteActivity extends Activity {
	private Button createButton;
	private Button insertButton;
	private Button updateButton;
	private Button updateRecordButton;
	private Button queryButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createButton = (Button) findViewById(R.id.createDatabase);
		updateButton = (Button) findViewById(R.id.updateDatabase);
		insertButton = (Button) findViewById(R.id.insert);
		updateRecordButton = (Button) findViewById(R.id.update);
		queryButton = (Button) findViewById(R.id.query);
		createButton.setOnClickListener(new CreateListener());
		updateButton.setOnClickListener(new UpdateListener());
		insertButton.setOnClickListener(new InsertListener());
		updateRecordButton.setOnClickListener(new UpdateRecordListener());
		queryButton.setOnClickListener(new QueryListener());
	}

	// 创建数据库
	class CreateListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_SQLite");
			SQLiteDatabase database = dbHelper.getReadableDatabase();
			// db.create(factory);

		}

	}
	// 更新

	class UpdateListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "test_mars_db", 2);
			SQLiteDatabase database = databaseHelper.getReadableDatabase();
			System.out.println("UpdateListener");

		}

	}

	class UpdateRecordListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "test_mars_db", 2);
			SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", "zhangsanfeng");
			sqLiteDatabase.update("user", values, "id=?", new String[] { "1" });
			System.out.println("UpdateRecordListener");

		}

	}

	class InsertListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			ContentValues values = new ContentValues();

			values.put("id", 1);
			values.put("name", "HouJun");
			// 先创建这个工具
			DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "test_mars_db", 2);
			// 用这个工具先拿到数据库
			SQLiteDatabase database = databaseHelper.getReadableDatabase();
			database.insert("user", null, values);
			System.out.println("insert!!!");

		}

	}

	class QueryListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "test_mars_db", 2);
			SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
			Cursor cursor = sqLiteDatabase.query("user", new String[] { "id", "name" }, "id=?",
					new String[] { "1" }, null, null, null);
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				System.out.println("query---->" + name);
			}

		}

	}

}
