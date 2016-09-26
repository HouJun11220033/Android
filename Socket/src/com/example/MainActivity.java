package com.example;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button startButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startButton = (Button) findViewById(R.id.startListener);
		startButton.setOnClickListener(new StartSocketListener());
	}

	class StartSocketListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 线程开始
			new ServerThread().start();

		}

	}

	class ServerThread extends Thread {
		public void run() {
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(4567);
				// 拿到链接
				Socket socket = serverSocket.accept();
				// 用Socket对象拿到输入流
				InputStream inputStream = socket.getInputStream();
				byte buffer[] = new byte[1024 * 4];
				int temp = 0;
				while ((temp = inputStream.read(buffer)) != -1) {
					// byte array to a string
					// 参数： data ,offset,bytecount
					System.out.println(new String(buffer, 0, temp));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					serverSocket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		}

	}

}
