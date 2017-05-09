package example.androidnnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by multidots on 6/23/2016.
 */
public class DirectReplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CharSequence replayMessage = getMessageText(getIntent());
        if (replayMessage != null) {
            Log.d("your message", replayMessage + "");
            MessageNotificationHandler.getInstance(this).addNewReplay(replayMessage.toString());
        }else {
            MessageNotificationHandler.getInstance(this).dismissDirectReplayNotification();
        }
        finish();
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(MessageNotificationHandler.KEY_TEXT_REPLY);
        }
        return null;
    }

}
