package com.example.myfoodbroadcast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class AddMeal extends AppCompatActivity {

    ImageView mealImage;
    Uri uri;
    Uri uriSender;
    EditText txt_name, txt_description, txt_calory, txt_link;
    ImageView imgSender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mealImage =  findViewById(R.id.iv_mealImage);
        txt_name =  findViewById(R.id.txt_meal_name);
        txt_description = findViewById(R.id.txt_meal_description);
        txt_calory = findViewById(R.id.txt_meal_calory);
        txt_link = findViewById(R.id.txt_meal_link);



    }


    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            uri = data.getData();
            mealImage.setImageURI(uri);
            uriSender = uri;
            imgSender = mealImage;



        } else{

            Toast.makeText(this, "You have not pick an image", Toast.LENGTH_LONG);


        }
    }

    public void btnAddMeal(View view) {

        String name = txt_name.getText().toString();
        String description = txt_description.getText().toString();
        String calory = txt_calory.getText().toString();
        String link = txt_link.getText().toString();


        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("IMG", uriSender.toString());
        i.putExtra("NAME", name);
        i.putExtra("DESCRIPTION", description);
        i.putExtra("Calories",calory);
        i.putExtra("LINK", link);
        startActivity(i);

    }
}
