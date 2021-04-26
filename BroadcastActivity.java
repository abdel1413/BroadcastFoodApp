package com.example.myfoodbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    public void Onclick(View view) {

        android.content.Intent intent = new android.content.Intent();
        intent.setAction("com.example.I_AM_HOME");
        sendBroadcast(intent);
    }
}