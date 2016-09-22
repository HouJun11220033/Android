package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	private SeekBar seekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar = (SeekBar) findViewById(R.id.seekbarId);
		seekBar.setMax(100);
		seekBar.setOnSeekBarChangeListener(new SeekBarListener());

	}

	private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			System.out.println(progress);

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			System.out.println("Start--->" + seekBar.getProgress());

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			System.out.println("Stop---->" + seekBar.getProgress());

		}

	}

}
