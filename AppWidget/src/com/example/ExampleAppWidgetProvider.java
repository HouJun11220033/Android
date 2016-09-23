package com.example;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
	private static final String UPDATE_ACTION = "com.example.UPDATE_APP_WIDGET";

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();

		if (UPDATE_ACTION.equals(action)) {
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
			remoteViews.setImageViewResource(R.id.imageId, R.drawable.ku);
			remoteViews.setTextViewText(R.id.widgetTextId, "test");
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			ComponentName componentName = new ComponentName(context, ExampleAppWidgetProvider.class);
			appWidgetManager.updateAppWidget(componentName, remoteViews);

			System.out.println("onReceive---->" + UPDATE_ACTION);
		} else {

			super.onReceive(context, intent);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		// Intent intent = new Intent(context, TargetActivity.class);
		Intent intent = new Intent();
		intent.setAction(UPDATE_ACTION);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, -1, intent, 0);
		// PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0,
		// intent, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
		// remoteViews.setOnClickPendingIntent(R.id.widgetButton,
		// pendingIntent2);
		remoteViews.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);

		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		// System.out.println("------>onUpdate");
		// for (int i = 0; i < appWidgetIds.length; i++) {
		// System.out.println(appWidgetIds[i]);
		// Intent intent = new Intent(context, TargetActivity.class);
		// PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
		// intent, 0);
		// RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
		// R.layout.example_appwidget);
		// remoteViews.setOnClickPendingIntent(R.id.widgetButton,
		// pendingIntent);
		// appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		//
		// }

		// super.onUpdate(context, appWidgetManager, appWidgetIds);
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
