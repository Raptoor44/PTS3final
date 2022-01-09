package com.example.pts3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.pts3.model.ListConteneurs;
import com.example.pts3.model.Notification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CANAL = "notifCanal";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String myMessasge = remoteMessage.getNotification().getBody();
        Log.d("firebaseMassage", "Vous venez de recevoir une notification: " + myMessasge);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification(ListConteneurs.getConteneursList());
        notification.checkForNotif();
        if (notification.isNotifier()) {
            myMessasge = notification.getMessage();
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CANAL);
        notificationBuilder.setContentTitle("Frigo Manager");
        notificationBuilder.setContentText(myMessasge);

        notificationBuilder.setContentIntent(pendingIntent);
        long[] vibration = {500, 1000};
        notificationBuilder.setVibrate(vibration);

        notificationBuilder.setSmallIcon(R.drawable._1_rangement_frigo);


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.notification_channel_id);
            String channelTitle = getString(R.string.notification_channel_title);
            String channelDesc = getString(R.string.notification_channel_desc);
            NotificationChannel channel = new NotificationChannel(channelId, channelTitle, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDesc);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelId);

        }
        notificationManager.notify(1, notificationBuilder.build());


    }
}
