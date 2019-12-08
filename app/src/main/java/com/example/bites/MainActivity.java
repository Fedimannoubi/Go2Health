package com.example.bites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WaterTime();

        findViewById(R.id.buttonSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               WaterNotification();
                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();

            }
        });
    }
        private void WaterNotification() {

            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //2.Build Notification with NotificationCompat.Builder
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Simple Notification")   //Set the title of Notification
                    .setContentText("I am a boring notification.")    //Set the text for notification
                    .setSmallIcon(R.drawable.ic_drink)   //Set the icon
                    .build();

            //Send the notification.
            mNotificationManager.notify(1, notification);
    }

    //timer for the
    public void WaterTime(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               // Toast.makeText(MainActivity.this, "test5", Toast.LENGTH_SHORT).show();
                WaterNotification();
            }
        }, 1000, 3000);
    }

}
