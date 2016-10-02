package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button clear = null;
	private Button calc = null;
	private EditText year = null;
	private EditText month = null;
	private EditText day = null;
	private EditText result = null;
	private TextView hint = null;

	public int isLeap(int x) {
		return x % 100 != 0 && x % 4 == 0 || x % 400 == 0 ? 1 : 0;
	}

	int[][] dayOfMonth = { { 0, 0 }, { 31, 31 }, { 28, 29 }, { 31, 31 }, { 30, 30 }, { 31, 31 }, { 30, 30 },
			{ 31, 31 }, { 31, 31 }, { 30, 30 }, { 31, 31 }, { 30, 30 }, { 31, 31 } };

	void nextdate(int year, int month, int day) {
		// isLeap(year);
		// 计算day
		// day++;
		// 从日开始判断！！！！！
		// 日--->月--->年
		if (year < 1800 || year > 2050) {
			hint.setText("the range of year is 1800-2050");
			result.setText("");
		} else {
			hint.setText("请输入数据后点击计算");
		}
		if (day < dayOfMonth[month][isLeap(year)]) {
			day++;
			result.setText(year + "年" + month + "月" + day + "日");

		} else if (day >= dayOfMonth[month][isLeap(year)]) {
			day = 1;
			month++;
			if (month > 12 && year < 2050) {
				month = 1;
				year++;
				// printf("Year:%d-Month:%d-Day:%d", year, month, day);
				result.setText(year + "年" + month + "月" + day + "日");
			} else {
				// printf("Year:%d-Month:%d-Day:%d", year, month, day);
				hint.setText("the range of year is 1800-2050");
			}
		}
		// 计算month

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		clear = (Button) MainActivity.this.findViewById(R.id.clear);
		calc = (Button) MainActivity.this.findViewById(R.id.clac);
		year = (EditText) MainActivity.this.findViewById(R.id.editText_year);
		day = (EditText) MainActivity.this.findViewById(R.id.editText_day);
		month = (EditText) MainActivity.this.findViewById(R.id.editText_month);
		result = (EditText) MainActivity.this.findViewById(R.id.editText_result);
		hint = (TextView) MainActivity.this.findViewById(R.id.hint);

		clear.setOnClickListener(new ClearData() {
			@Override
			public void onClick(View v) {
				year.setText("0");
				month.setText("0");
				day.setText("0");
				result.setText("0");
			}

		});
		calc.setOnClickListener(new Calculate() {

			// nextdate(y,m,d);

		});

	}

	class ClearData implements OnClickListener {

		@Override
		public void onClick(View v) {

		}

	}

	class Calculate implements OnClickListener {

		@Override
		public void onClick(View v) {
			int y = Integer.valueOf(year.getText().toString());

			int m = Integer.valueOf(month.getText().toString());
			int d = Integer.valueOf(day.getText().toString());
			nextdate(y, m, d);

		}

	}

}
