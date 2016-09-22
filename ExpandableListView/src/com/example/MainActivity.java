package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends ExpandableListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
		Map<String, String> group1 = new HashMap<String, String>();
		//KeyΪList������
		group1.put("group", "group1");
		Map<String, String> group2 = new HashMap<String, String>();
		group2.put("group", "group2");
		groups.add(group1);
		groups.add(group2);

		// ����һ��List����List����Ϊ��һ��һ����Ŀ�ṩ������Ŀ������
		List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
		Map<String, String> child1Date1 = new HashMap<String, String>();
		child1Date1.put("child", "child1Data1");
		child1.add(child1Date1);
		Map<String, String> child1Data2 = new HashMap<String, String>();
		child1Data2.put("child", "child1Data2");
		child1.add(child1Data2);

		// ����һ��List,��List����Ϊ�ڶ���һ����Ŀ�ṩ������Ŀ������
		List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
		Map<String, String> child2Data = new HashMap<String, String>();
		child2Data.put("child", "child2Data");
		child2.add(child2Data);
		// ����һ��List����List���������洢���еĶ�����Ŀ������
		List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();
		childs.add(child1);
		childs.add(child2);

		SimpleExpandableListAdapter simAdapter = new SimpleExpandableListAdapter(
				this, groups, R.layout.group, new String[] { "group" },
				new int[] { R.id.groupTo }, childs, R.layout.child,
				new String[] { "child" }, new int[] { R.id.childTo });
		setListAdapter(simAdapter);

	}
}
