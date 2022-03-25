package com.example.duanmau_mob2041_ytdnph12917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.duanmau_mob2041_ytdnph12917.Login.DangNhap;

public class ScreenPlash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_plash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DangNhap.class));
                finish();
            }
        }, 1500);
    }
}