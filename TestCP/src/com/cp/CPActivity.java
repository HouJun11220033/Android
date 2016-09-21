package com.cp;

import com.cp.FirstProviderMetaData.UserTableMetaData;
import com.example.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CPActivity extends Activity {
	private Button insertButton;
	private Button queryButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		insertButton = (Button) findViewById(R.id.insert);
		queryButton = (Button) findViewById(R.id.query);
		insertButton.setOnClickListener(new InsertListener());
		queryButton.setOnClickListener(new QueryListener());

	}

	class InsertListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			ContentValues values = new ContentValues();
			values.put(FirstProviderMetaData.UserTableMetaData.USER_NAME, "JDroid");
			// 这个插入得先有数据库，
			Uri uri = getContentResolver().insert(FirstProviderMetaData.UserTableMetaData.CONTENT_URI,
					values);
			System.out.println("uri--->" + uri.toString());

		}

	}

	class QueryListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Cursor cursor = getContentResolver().query(FirstProviderMetaData.UserTableMetaData.CONTENT_URI,
					null, null, null, null);
			while (cursor.moveToNext()) {
				System.out.println(cursor.getString(cursor.getColumnIndex(UserTableMetaData.USER_NAME)));

			}

		}

	}

}
