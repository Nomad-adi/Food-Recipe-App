package com.example.foodrecepieapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecepieapp.R;
import com.example.foodrecepieapp.activities.MainActivity;
import com.example.foodrecepieapp.activities.Manual;
import com.example.foodrecepieapp.adapter.ReceipeAdapter.ViewHolder;
import com.example.foodrecepieapp.model.Category_model;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category_model> category_modelArrayList;
    Context context;

    boolean check_activity = false;
    public CategoryAdapter(@NonNull ArrayList<Category_model> category_modelArrayList, Context context) {
        this.category_modelArrayList = category_modelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(context.equals(MainActivity.class)){
            view = LayoutInflater.from(context).inflate(R.layout.latestmealrcv,parent,false);
            check_activity = true;
        }
        else{
            view = LayoutInflater.from(context).inflate(R.layout.category_recycleview,parent,false);
        }

        ViewHolder catViewHolder = new ViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        if (check_activity){
            Glide.with(context).load(category_modelArrayList.get(position).getMeal_url()).into(holder.latest_meal_img);
            holder.latest_tv.setText(category_modelArrayList.get(position).getMeal_title());
        }
        else{
            holder.dynamic_text.setText(category_modelArrayList.get(position).getMeal_title());
            Glide.with(context).load(category_modelArrayList.get(position).getMeal_url()).into(holder.dynamic_img);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent item_intent = new Intent(context, Manual.class);
                item_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                item_intent.putExtra("meal_id", category_modelArrayList.get(position).getMeal_id());
                item_intent.putExtra("meal_url", category_modelArrayList.get(position).getMeal_url());
                item_intent.putExtra("meal_title", category_modelArrayList.get(position).getMeal_title());
                context.startActivity(item_intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return category_modelArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView dynamic_text;
        TextView latest_tv;
        ImageView dynamic_img;
        ImageView latest_meal_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dynamic_text = itemView.findViewById(R.id.dynamic_text);
            dynamic_img = itemView.findViewById(R.id.dynamic_img);
            latest_meal_img = itemView.findViewById(R.id.latest_img);
            latest_tv = itemView.findViewById(R.id.latest_tv);
        }
    }
}
