package com.honeywell.h223490.stock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by 20068032 on 24-11-2016.
 */


/**
 * Created by Belal on 4/15/2016.
 */

//Class is extending GcmListenerService
public class GCMPushReceiverService extends GcmListenerService {
    private static final String TAG = GCMPushReceiverService.class.getSimpleName();
    String pushMessage;
    private boolean isAppForground = false;
    public static int MY_NOTIFICATION_ID = 0;
    private String siteName;

    //This method will be called on every new message received
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Getting the message from the bundle
        pushMessage = data.getString("message");
        siteName = data.getString("site");

        MY_NOTIFICATION_ID++;
        Log.d(TAG,"received message:" + pushMessage);
        sendNotification(pushMessage);
    }

    //This method is generating a notification and displaying the notification
    private void sendNotification(final String message) {





        Intent intent = new Intent(this, Notification.class);


        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |

                Intent.FLAG_ACTIVITY_NEW_TASK);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, MY_NOTIFICATION_ID  /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);



        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)

                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("STOCK Alert")

                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setAutoCancel(false)

                .setSound(defaultSoundUri)
                // .setNumber(++message)
                //.setPriority(1)
                .setContentIntent(pendingIntent);












        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(MY_NOTIFICATION_ID /* ID of notification */, notificationBuilder.build());






        // write notification code here
        // Intent notificationIntent = new Intent(this, Notifydialog.class);
        //  startActivity(notificationIntent);



    }

}

