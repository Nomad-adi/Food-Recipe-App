package com.example.foodrecepieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecepieapp.R;
import com.example.foodrecepieapp.activities.Category;
import com.example.foodrecepieapp.activities.MainActivity;
import com.example.foodrecepieapp.activities.SplashScreen;
import com.example.foodrecepieapp.model.RecipieModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ReceipeAdapter extends RecyclerView.Adapter<ReceipeAdapter.ViewHolder> {

    ArrayList<RecipieModel> recipieModelArrayList;
    Context context;

    public ReceipeAdapter(Context context, ArrayList<RecipieModel> recipieModelArrayList) {
        this.context = context;
        this.recipieModelArrayList = recipieModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleviewlayout,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceipeAdapter.ViewHolder holder, int position) {
        holder.title.setText(recipieModelArrayList.get(position).getTitle());
        Glide.with(context).load(recipieModelArrayList.get(position).getImgUrl()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Category.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // additional for android 9 to call start activity intent form another activity
                intent.putExtra("desc", recipieModelArrayList.get(position).getDesc());
                intent.putExtra("imgUrl", recipieModelArrayList.get(position).getImgUrl());
                intent.putExtra("title", recipieModelArrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipieModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.imgview);

        }
    }
}
