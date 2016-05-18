package com.example.sabod.testapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashScreen extends Activity{

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    private static final int PROGRESS = 0x1;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mProgress = (ProgressBar) findViewById(R.id.progress_bar);

/*
        new Handler().postDelayed(new Runnable() {

            // Start lengthy operation in a background thread
            new Thread(new Runnable() {
                public void run() {
                    while (mProgressStatus < 100) {
                        // mProgressStatus = doWork();

                        // Update the progress bar
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgress.setProgress(mProgressStatus);
                                mProgressStatus =+10;
                            }
                        });
                    }
                }
            }).start();

            @Override
           public void run() {
               // This method will be executed once the timer is over
               // Start your app main activity
               Intent i = new Intent(SplashScreen.this, MainActivity.class);
               startActivity(i);

               // close this activity
               finish();
           }
       }, SPLASH_TIME_OUT);

*/
    }

}
