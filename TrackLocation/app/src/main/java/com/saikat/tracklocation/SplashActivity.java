package com.saikat.tracklocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      Thread thread = new Thread(){
          public void run(){
            try {
                sleep(3000);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
          }
      };
      thread.start();

    }
}