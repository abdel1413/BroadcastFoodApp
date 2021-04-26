package com.example.myfoodbroadcast;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.MealItemHolder> {

    private Context mContext;
    private List<MealItem> myMealItemList;

    public MealItemAdapter(Context mContext, List<MealItem> myMealItemList) {
        this.mContext = mContext;
        this.myMealItemList = myMealItemList;

    }

    public Bitmap intentDrawableConverterToBitmap(Drawable drawable){

        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        return bitmap;
    }

    public byte[] bitmapCompression(Bitmap bitmap){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        //setresult.putExtra("BMP",bytes);

        return bytes;

    }


    @NonNull
    @Override
    public MealItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item, parent, false);

        return new MealItemHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MealItemHolder holder, final int position) {

        holder.imageView.setImageDrawable(myMealItemList.get(position).getItemImage());
        holder.mTitle.setText(myMealItemList.get(position).getItemName());
        holder.mDescription.setText(myMealItemList.get(position).getItemDescription());
        holder.mLink.setText(myMealItemList.get(position).getItemLink());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Magic tricks -> Drawable -> Bitmap ->byte so the info can be passed through intent
                Bitmap bitmapForByte = intentDrawableConverterToBitmap(myMealItemList.get(holder.getAdapterPosition()).getItemImage());
                byte [] byteForIntent = bitmapCompression(bitmapForByte);

                Global.img = bitmapForByte;

                Intent intent = new Intent(mContext, Details.class);
                //intent.putExtra("Image", byteForIntent );
                intent.putExtra("Description", myMealItemList.get(holder.getAdapterPosition()).getItemDescription());
                intent.putExtra("LINK", myMealItemList.get(holder.getAdapterPosition()).getItemLink());

                mContext.startActivity(intent);

                holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        myMealItemList.remove(position);
                        notifyDataSetChanged();


                        return false;
                    }
                });

            }
        });




    }

    @Override
    public int getItemCount() {

        try{
            return myMealItemList.size();
        }
        catch(NullPointerException e){

            return 0;
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    class MealItemHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView mTitle, mDescription, mLink;
        CardView mCardView;

        public MealItemHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.ivImage);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mDescription = itemView.findViewById(R.id.tvDescription);
            mLink = itemView.findViewById(R.id.tvLink);

            mCardView = itemView.findViewById(R.id.MyCardView);


        }
    }
}
