package com.example;

import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner = (Spinner) findViewById(R.id.mySpinner);
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item,
				R.id.textViewId, list);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner.setAdapter(adapter);
		spinner.setPrompt("TEST!!!");
		spinner.setOnItemSelectedListener(new SpinnerOnSelectedListener());

	}

	class SpinnerOnSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			String selected = adapterView.getItemAtPosition(position)
					.toString();
			System.out.println(selected);

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			System.out.println("Nothing Selected !");

		}

	}

}
