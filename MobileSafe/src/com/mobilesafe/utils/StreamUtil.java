package com.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	/**
	 * @param inputStream:流对象
	 * @return 把流对象转换成字符串，返回null代表异常
	 */
	public static String stream2String(InputStream inputStream) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int temp = -1;
		try {
			while ((temp = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, temp);
			}
			return bos.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				bos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;

	}

}
