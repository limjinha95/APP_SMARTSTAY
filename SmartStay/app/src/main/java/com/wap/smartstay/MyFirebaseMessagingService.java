package com.wap.smartstay;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    SharedPreferences pref;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        pref = getSharedPreferences("Switch", Activity.MODE_PRIVATE);
        if(!pref.getBoolean("sw4", false)){
        }
        else {
            Map<String, String> data = remoteMessage.getData();
            if (data != null) {
                Log.d(TAG, "data = " + data);
                sendNotification(data.get("SmartStay"));
            }
            RemoteMessage.Notification noti = remoteMessage.getNotification();
            if (noti != null) {
                String title = noti.getTitle();
                Log.d(TAG, "Title = " + title);
                String body = noti.getBody();
                Log.d(TAG, "body = " + body);
            }
        }
    }

    private void sendNotification(String messageBody) {
        pref = getSharedPreferences("Switch", Activity.MODE_PRIVATE);
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.addgroup);
        notificationBuilder.setContentTitle("Smart Stay");
        notificationBuilder.setContentText(messageBody);
        notificationBuilder.setAutoCancel(true);
        if(pref.getBoolean("sw2", false))
            notificationBuilder.setSound(defaultSoundUri);
        if(pref.getBoolean("sw3", false))
            notificationBuilder.setVibrate(new long[]{1000,1000,1000});
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setPriority(Notification.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

    }
}
