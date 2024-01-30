package com.example.whatever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button classesButton = findViewById(R.id.classesButton);
        classesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Classes.class));
            }
        });

        Button notifButton = findViewById(R.id.notifButton);
        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the Activity (Context)
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                Context context = v.getContext(); // Get context from the View

                NotificationCompat.Builder myBuild = new NotificationCompat.Builder(context);
                myBuild.setContentTitle("HIIII");
                myBuild.setContentText("HEYYY");
                myBuild.setSmallIcon(R.drawable.alarm_on);

                NotificationManagerCompat manager = NotificationManagerCompat.from(context);
                manager.notify(1, myBuild.build()); // Provide a notification ID
            }
        });
    }




}