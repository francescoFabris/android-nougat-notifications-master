package example.androidnnotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NotificationOpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_open);

        MessageNotificationHandler.getInstance(this).dismissDirectReplayNotification();
    }



}
