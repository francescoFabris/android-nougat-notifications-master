package example.androidnnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;

/**
 * Created by multidots on 6/23/2016.
 */
public class MessageNotificationHandler {
    // Where should direct replies be put in the intent bundle (can be any string)
    public static final String KEY_TEXT_REPLY = "key_text_reply";
    public static final int BIG_TEXT_NOTIFICATION_KEY = 0;
    private static NotificationManager notificationManager;
    private static ArrayList<android.support.v4.app.NotificationCompat.MessagingStyle.Message> mMessages;
    private static volatile MessageNotificationHandler sInstance;
    private Context context;

    public MessageNotificationHandler(Context context){
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //generate fake data
        generateData();
    }

    public static MessageNotificationHandler getInstance(Context context){
        if (sInstance == null)sInstance = new MessageNotificationHandler(context);
        return sInstance;
    }

    private void generateData() {
        mMessages = new ArrayList<>();

        android.support.v4.app.NotificationCompat.MessagingStyle.Message message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Nasta ma shu mangavanu che?", System.currentTimeMillis() - 60000, "Yasin");
        mMessages.add(message);

        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Puff", System.currentTimeMillis() - 30000, "Rajan");
        mMessages.add(message);

        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Dhosa vada", System.currentTimeMillis() - 30000, "Mirant");
        mMessages.add(message);

        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Marigold 10 vaalu", System.currentTimeMillis() - 30000, "Hiral");
        mMessages.add(message);

        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Sev mamra (Balaji na j)", System.currentTimeMillis() - 30000, "Parasbhai");
        mMessages.add(message);
    }

    public void displayDirectReplayDemo() {
        PendingIntent pIntent = PendingIntent.getActivity(context,
                (int) System.currentTimeMillis(),
                new Intent(context, NotificationOpenActivity.class), 0);

        // Create the RemoteInput specifying this key
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Type your reply")
                .build();

        PendingIntent directReplayIntent = PendingIntent.getActivity(context,
                (int) System.currentTimeMillis(),
                new Intent(context, DirectReplayActivity.class), 0);

        // Add to your action, enabling Direct Reply for it
        NotificationCompat.Action directReplayAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_replay, "Replay", directReplayIntent)
                        .addRemoteInput(remoteInput)
                        .setAllowGeneratedReplies(true)
                        .build();

        //prepare message style notification
        android.support.v4.app.NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("Me")
                .setConversationTitle("Ajano nasto...");
        for (int i = 0; i < mMessages.size(); i++) style.addMessage(mMessages.get(i));

        Notification noti = new NotificationCompat.Builder(context)
                .setContentText("This is test notification.")
                .setAutoCancel(false)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(style)
                .setContentIntent(pIntent)
                .addAction(directReplayAction)
                .build();

        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(BIG_TEXT_NOTIFICATION_KEY, noti);
    }

    public void addNewReplay(String replay) {
        mMessages.add(new android.support.v4.app.NotificationCompat.MessagingStyle.Message(replay, System.currentTimeMillis(), null));

        //update notification
        displayDirectReplayDemo();
    }

    public void dismissDirectReplayNotification() {
        notificationManager.cancel(BIG_TEXT_NOTIFICATION_KEY);
    }
}
