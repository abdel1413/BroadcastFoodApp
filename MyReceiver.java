package com.example.myfoodbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MyReceiver extends BroadcastReceiver {

    private java.util.ArrayList<com.example.myfoodbroadcast.MealItem> items;
    protected static final String ACTION_CUSTOM_BROADCAST =
            "" +"com.example.I_AM_HOME";

    private final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Random random = new Random();
        int index = random.nextInt();

//        String act = intent.getAction();
//        MealItem curMeal = items.get(index);
//        Intent start = new Intent(context, Details.class);
//        start.putExtra("Title", curMeal.getItemName());
//        start.putExtra("Description", curMeal.getItemDescription());
//        start.putExtra("Calories", curMeal.getItemCalory());
//        start.putExtra("Link", curMeal.getItemLink());
//        context.startActivity(start);
//        android.widget.Toast.makeText(context, "Happy Cooking " + curMeal.getItemName(),Toast.LENGTH_LONG).show();
//        android.util.Log.d(TAG, "index = " + index);



        String action = intent.getAction();
        if(action != null) {
            String  toastMessage = context.getString(R.string.unknown_action);
            if(action .equals(ACTION_CUSTOM_BROADCAST)) {
               String title = intent.getStringExtra("title");
                toastMessage = "Happy cooking " + title;
            }
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
        }
    }
}