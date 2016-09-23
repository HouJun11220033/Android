package com.example;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {

		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		System.out.println("------>onUpdate");
		for (int i = 0; i < appWidgetIds.length; i++) {
			System.out.println(appWidgetIds[i]);
			Intent intent = new Intent(context, TargetActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
			remoteViews.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);

		}

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		System.out.println("------>onDelete");

		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		System.out.println("------>onEnabled");

		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		System.out.println("------>onDisabled");

		super.onDisabled(context);
	}

}
