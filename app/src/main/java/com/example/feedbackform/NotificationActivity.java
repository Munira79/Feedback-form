package com.example.feedbackform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private Switch notificationSwitch;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "notification_prefs";
    private static final String NOTIFICATION_STATUS = "notification_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationSwitch = findViewById(R.id.notificationSwitch);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        boolean isNotificationOn = sharedPreferences.getBoolean(NOTIFICATION_STATUS, false);
        notificationSwitch.setChecked(isNotificationOn);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(NOTIFICATION_STATUS, isChecked);
            editor.apply();

            String message = isChecked ? "Notifications Enabled" : "Notifications Disabled";
            Toast.makeText(NotificationActivity.this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
