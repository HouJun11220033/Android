package com.example;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonUtils {
	public void parseJson(String jsonData) {
		try {
			JsonReader jsonReader = new JsonReader(new StringReader(jsonData));
			// 开始解析数组
			jsonReader.beginArray();
			while (jsonReader.hasNext()) {
				// 开始解析对象
				jsonReader.beginObject();
				while (jsonReader.hasNext()) {
					String tagName = jsonReader.nextName();
					if (tagName.equals("name")) {
						System.out.println("name--->" + jsonReader.nextString());
					} else if (tagName.equals("age")) {
						System.out.println("age--->" + jsonReader.nextInt());

					}
				}
				jsonReader.endObject();

			}
			jsonReader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void parseUserFromJson(String jsonData) {
		Type listType = new TypeToken<LinkedList<User>>() {
		}.getType();

		Gson gson = new Gson();
		LinkedList<User> users = gson.fromJson(jsonData, listType);
		// User user = gson.fromJson(jsonData, User.class);
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			System.out.println("name--->" + user.getName());

			System.out.println("age--->" + user.getAge());

		}

	}

}
