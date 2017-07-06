package com.rahulserver.parsenotificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rahulserver on 4/18/2015.
 */
public class MyCustomParsePushReceiver extends ParsePushBroadcastReceiver {
    String TAG="MyCustomParsePushReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            Log.d(TAG, json.getString("alert").toString());

            final String notificationTitle = json.getString("title").toString();
            final String notificationContent = json.getString("alert").toString();
            final String uri = json.getString("uri");

            Intent resultIntent = null;
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);


            resultIntent = new Intent(context, MainActivity.class);
            stackBuilder.addParentStack(MainActivity.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


//Customize your notification - sample code
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(notificationTitle)
                            .setContentText(notificationContent);

            int mNotificationId = 001;
            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, builder.build());


        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }

    }
}
