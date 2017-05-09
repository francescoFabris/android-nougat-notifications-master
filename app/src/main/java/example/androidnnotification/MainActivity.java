package example.androidnnotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @see 'https://developer.android.com/preview/features/notification-updates.html'
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.direct_replay).setOnClickListener(this);
        findViewById(R.id.bundle_notification).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.direct_replay:
                MessageNotificationHandler.getInstance(this).displayDirectReplayDemo();
                break;
            case R.id.bundle_notification:
                new BundledNotificationHelper(this).generateBundle(this);
                break;
        }
    }
}
