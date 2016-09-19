package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {
	private String SDCardRoot;

	public FileUtils() {
		SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

	}

	// �����ļ�
	public File createFileInSDCard(String fileName, String dir) throws IOException {
		File file = new File(SDCardRoot + dir + File.separator + fileName);
		file.createNewFile();
		return file;

	}

	// ����Ŀ¼
	public File createSDDir(String dir) {
		File dirFile = new File(SDCardRoot + dir + File.separator);
		System.out.println(dirFile.mkdirs());
		return dirFile;

	}

	/**
	 * �ж�SD���ϵ��ļ����Ƿ����
	 */
	public boolean isFileExist(String fileName, String path) {
		File file = new File(SDCardRoot + path + File.separator + fileName);
		return file.exists();
	}

	/**
	 * ��һ��InputStream��������д�뵽SD����
	 */
	public File write2SDFromInput(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream outputStream = null;
		try {
			createSDDir(path);
			// �������ļ�

			file = createFileInSDCard(fileName, path);
			// ���outputStream�������ļ��õ�������
			outputStream = new FileOutputStream(file);

			byte buffer[] = new byte[4 * 1024];
			int temp;
			// �������������ȡ
			// �������д
			while ((temp = input.read(buffer)) != -1) {
				// �����û��read����
				outputStream.write(buffer, 0, temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return file;

	}

}
