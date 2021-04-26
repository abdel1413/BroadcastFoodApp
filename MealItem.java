package com.example.myfoodbroadcast;
import android.graphics.drawable.Drawable;

public class MealItem {

    private String itemName;
    private String itemDescription;
    private String itemCalory;
    private Drawable itemImage;
    private String itemLink;

    public MealItem(String itemName, String itemDescription, Drawable itemImage, String calory, String itemLink) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        this.itemCalory = calory;
        this.itemLink = itemLink;
    }



    public Drawable getItemImage()
    {
        return itemImage;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }


    public String getItemCalory()
    {
        return itemCalory;
    }

    public String getItemLink()
    {
        return itemLink;
    }

}