package com.example.bites;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private AppBarConfiguration mAppBarConfiguration;
    NotificationManager mNotificationManager;

    private TextView userName;
    private TextView userEmail;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = findViewById(R.id.fab);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //snake bar message icon action
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //to cont.....

        navigationView = findViewById(R.id.nav_view);

        //get the current user logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //declaration of the header
        View headerLayout = navigationView.getHeaderView(0);



        userName = headerLayout.findViewById(R.id.userName);
        userName.setText(currentUser.getDisplayName());


        userEmail = headerLayout.findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmail());

        //use Picasso to get and set teh photo og the user
        imageView = headerLayout.findViewById(R.id.imageView);
        Picasso.get().load(currentUser.getPhotoUrl()).into(imageView);

        //notifications timer
        WaterTime();





    }

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_disconnect:
                disconnect();break;
            case R.id.action_settings:
                settings();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void signOut() {

        FirebaseAuth.getInstance().signOut();

    }

    public void disconnect() {
        signOut();
        finish();
    }

    public void settings(){
        Intent myIntent = new Intent(this, Settings.class);
        startActivity(myIntent);
    }

    public void WaterTime(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Toast.makeText(MainActivity.this, "test5", Toast.LENGTH_SHORT).show();
                WaterNotification();
            }
            //7200000 = 2 hours
        }, 1000, 7200000);
    }

    private void WaterNotification() {

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //2.Build Notification with NotificationCompat.Builder
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Water Reminder")   //Set the title of Notification
                .setContentText("Time to drink water!")    //Set the text for notification
                .setSmallIcon(R.drawable.ic_drink)   //Set the icon
                .build();

        //Send the notification.
        mNotificationManager.notify(1, notification);
    }



}