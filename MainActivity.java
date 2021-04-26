package com.example.myfoodbroadcast;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    List<MealItem> myMealItemList;
    MealItem mMealItem;

    private static final  String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";

    private MyReceiver myReceiver = new MyReceiver();

    RecyclerView mRecycleView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        this.registerReceiver(myReceiver,filter);

        //get instance of LocalbroadcastManager to register receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                new IntentFilter(ACTION_CUSTOM_BROADCAST));


        mRecycleView = findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecycleView.setLayoutManager(gridLayoutManager);

        myMealItemList = new ArrayList<>();

        MealItemAdapter mealItemAdapter = new MealItemAdapter(MainActivity.this, myMealItemList);
        mRecycleView.setAdapter(mealItemAdapter);

        addDefaultMeal("Spicy Peanut Chicken",
                "This colorful stir-fry gets its kick from a dash of red pepper.", R.drawable.spicy_rice,
                "189",
                "For more information visit: https://www.bettycrocker.com/recipes/spicy-peanut-chicken/4d432df6-a1a8-499e-8a43-b0703ffe8800" );

        addDefaultMeal("Santa Fe Chicken Skillet",
                "With zesty spiced chicken breasts, black beans and a rainbow of vegetables, including corn, green chiles, onion, peppers and zucchini, this skillet makes for a satisfying and well-balanced family meal. The double dose of protein, from beans and chicken, ensures even hearty eaters will get their fill, while those watching their calories can enjoy this meal without a second thought. For a perfect finish, top this meal with a squeeze of fresh lime, sprinkle of chopped cilantro and a crumble of queso fresco!",
                 R.drawable.santa_fe,
                "260",
                "For more information visit: https://www.bettycrocker.com/recipes/santa-fe-chicken-skillet/3a0a60dd-1881-4799-a910-dc4b57d6920d" );

        addDefaultMeal("Classic Meatballs",
                "Why reinvent the wheel? For generations, home cooks have relied on this classic meatball recipe for its infallibility and ease—and when we say they’re easy, we really mean it. ", R.drawable.meat_ball,
                "129",
                "For more information visit: https://www.bettycrocker.com/recipes/classic-meatballs/2959910f-1b27-438a-9085-d40b1950db20" );

        addDefaultMeal("Strawberry Frosted Layer Cake",
                "The star of this beautiful cake is the fresh strawberry buttercream frosting that’s bursting with summer flavor. As impressive as it looks, this cake is made easy with Betty Crocker™ Super Moist™ white cake mix.", R.drawable.cake,
                "240",
                "For more information visit: https://www.bettycrocker.com/recipes/strawberry-frosted-layer-cake/3d687c23-1c56-49d4-85ed-bd190441a284" );


        try {
            add_Meal();
        } catch (Exception e){
            e.printStackTrace();
        }


//        MealItemAdapter mealItemAdapter = new MealItemAdapter(MainActivity.this, myMealItemList);
//        mRecycleView.setAdapter(mealItemAdapter);

    }



    public void SendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }


    public void addDefaultMeal(String name, String description, int imageRidResource, String calory_path, String link){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageRidResource);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);

        myMealItemList.add(new MealItem(name,description,drawable,calory_path,link));

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void add_Meal()  {

        try {

            Intent intent = getIntent();
            String name_path = intent.getStringExtra("NAME");
            String description_path = intent.getStringExtra("DESCRIPTION");

            String image_path = intent.getStringExtra("IMG");
            String  calory_path = intent.getStringExtra("Calory");
            String link_path = intent.getStringExtra("LINK");

            Uri fileUri = Uri.parse(image_path);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            Drawable drawableTest = new BitmapDrawable(getResources(), bitmap);

            myMealItemList.add(new MealItem(name_path,description_path,
                    drawableTest, calory_path, link_path));

        }catch ( IOException e){

            Log.d("tagname","string you want to execute");
        }
    }



    public void btn_addActivity(View view) {

        startActivity(new Intent(this, AddMeal.class));
    }


    public void Onclick(View view) {
        Log.d(TAG, "Inside Onclick method");
        int getRandFood = myMealItemList.size();
        int  stringToNum = (int)(Math.random()*getRandFood);
       mMealItem = myMealItemList.get(stringToNum);
      Intent intent = new Intent(ACTION_CUSTOM_BROADCAST);
      intent.putExtra("title", mMealItem.getItemName());
//        intent.putExtra("description", currMeal.getItemDescription());
//        intent.putExtra("calory", currMeal.getItemCalory());
//        intent.putExtra("links", currMeal.getItemLink());


        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        Log.d(TAG, "inside detail method");

        Intent recipesIntent = new Intent(getApplicationContext(), Details.class);
        recipesIntent.putExtra("title", mMealItem.getItemName());
        recipesIntent.putExtra("description", mMealItem.getItemDescription());
        recipesIntent.putExtra("calory", mMealItem.getItemCalory());
        recipesIntent.putExtra("links", mMealItem.getItemLink());
        Log.d(TAG, "End detail method");
        startActivity(recipesIntent);

        intent.setAction(ACTION_CUSTOM_BROADCAST);
         sendBroadcast(intent);

        Log.d(TAG, "end of Onclick method");
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(myReceiver);
        super.onDestroy();

        //instance of LocatBroadcast to unregister myreceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);

    }


}
