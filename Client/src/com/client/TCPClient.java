package com.client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
	public static void main(String[] args) {

		try {
			Socket socket = new Socket("169.254.149.125", 4567);
			InputStream inputStream = new FileInputStream("f://words.txt");
			OutputStream outputStream = socket.getOutputStream();
			byte buffer[] = new byte[4 * 1024];
			int temp = 0;
			while ((temp = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
