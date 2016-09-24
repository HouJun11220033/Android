package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private String jsonData = "{\"name\":\"Michael\",\"age\":20}";
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.buttonId);
		button.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			JsonUtils jsonUtils = new JsonUtils();
			// jsonUtils.parseJson(jsonData);
			jsonUtils.parseUserFromJson(jsonData);

		}

	}

}
