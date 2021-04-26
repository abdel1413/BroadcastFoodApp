package com.example.myfoodbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {


    TextView mealDescription, mealLink;
    ImageView mealImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mealDescription = findViewById(R.id.txtDescription);
        mealImage = findViewById(R.id.ivImage);
        mealLink = findViewById(R.id.txtLink);


        Bundle mBundle = getIntent().getExtras();

        mealImage.setImageBitmap(Global.img);


        if(mBundle != null){

            mealLink.setText(mBundle.getString("LINK"));
            mealDescription.setText(mBundle.getString("Description"));

        }


    }
}