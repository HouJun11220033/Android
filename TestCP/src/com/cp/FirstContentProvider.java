package com.cp;

import java.util.HashMap;

import com.cp.FirstProviderMetaData.UserTableMetaData;
import com.sqlite3.DatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class FirstContentProvider extends ContentProvider {
	public static final UriMatcher URI_MATCHER;
	public static final int INCOMING_USER_COLLECTION = 1;
	public static final int INCOMING_USER_SINGLE = 2;

	private DatabaseHelper databaseHelper;

	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(FirstProviderMetaData.AUTHORITY, "/users", INCOMING_USER_COLLECTION);
		URI_MATCHER.addURI(FirstProviderMetaData.AUTHORITY, "/users/#", INCOMING_USER_SINGLE);
	}
	public static HashMap<String, String> userProjectionMap;
	static {
		userProjectionMap = new HashMap<String, String>();
		userProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}

	@Override
	public boolean onCreate() {

		// 初始化时就生成DatabaseHelper对象
		databaseHelper = new DatabaseHelper(getContext(), FirstProviderMetaData.DATABASE_NAME);
		System.out.println("onCreate");

		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
		switch (URI_MATCHER.match(uri)) {
		case INCOMING_USER_COLLECTION:
			sqLiteQueryBuilder.setTables(UserTableMetaData.TABLE_NAME);
			sqLiteQueryBuilder.setProjectionMap(userProjectionMap);

			break;
		case INCOMING_USER_SINGLE:
			sqLiteQueryBuilder.setTables(UserTableMetaData.TABLE_NAME);
			sqLiteQueryBuilder.setProjectionMap(userProjectionMap);
			sqLiteQueryBuilder.appendWhere(UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));
			break;

		}
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = UserTableMetaData.DEFAULT_SORT_ORDER;

		} else {
			orderBy = sortOrder;
		}
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor c = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		System.out.println("query");
		return c;
	}

	@Override
	public String getType(Uri uri) {
		System.out.println("getType");
		switch (URI_MATCHER.match(uri)) {
		case INCOMING_USER_COLLECTION:
			return UserTableMetaData.CONTENT_TYPE;
		case INCOMING_USER_SINGLE:
			return UserTableMetaData.CONTENT_TYPE_ITEM;

		default:
			throw new IllegalArgumentException("Unknow URI" + uri);
		}

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		System.out.println("insert");
		SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
		Long rowId = sqLiteDatabase.insert(UserTableMetaData.TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertedUserUri = ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;

		}
		throw new SQLException("Failed to insert row into" + uri);

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		System.out.println("delete");
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		System.out.println("update");
		return 0;
	}

}
