package com.example;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends Activity {

	private Button showDatePickerButton = null;
	private static final int DATE_PICKER_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showDatePickerButton = (Button) findViewById(R.id.showDatePickerButton);
		showDatePickerButton.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			showDialog(DATE_PICKER_ID);

		}

	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			System.out.println(year + "-" + monthOfYear + "-" + dayOfMonth);

		}
	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			return new DatePickerDialog(this, onDateSetListener, 2016, 11, 25);

		}
		return null;

	}

}
