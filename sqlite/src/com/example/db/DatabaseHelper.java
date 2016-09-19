package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;

	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		// context :to use to open or create the database
		// name :of the database file, or null for an in-memory database
		// version number of the database (starting at 1); if the database is
		// older,
		// onUpgrade will be used to upgrade the database;
		// if the database is newer, onDowngrade will be used to downgrade the
		// database

		super(context, name, factory, version);

	}

	// 这个构造方法用于创建数据库
	public DatabaseHelper(Context context, String name) {
		this(context, name, VERSION);

	}

	public DatabaseHelper(Context context, String name, int version) {

		this(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("create a Database");
		db.execSQL("create table user(id int ,name varchar(20))");
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("update a Database");
		// TODO Auto-generated method stub

	}

}
