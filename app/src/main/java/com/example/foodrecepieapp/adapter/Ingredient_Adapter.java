package com.example.foodrecepieapp.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecepieapp.R;
import com.example.foodrecepieapp.model.Ingredient_Model;

import java.util.ArrayList;

public class Ingredient_Adapter extends RecyclerView.Adapter<Ingredient_Adapter.ViewHolder> {

    Context context;
    ArrayList<Ingredient_Model> ingredient_modelArrayList;

    public Ingredient_Adapter(Context context, ArrayList<Ingredient_Model> ingredient_modelArrayList) {
        this.context = context;
        this.ingredient_modelArrayList = ingredient_modelArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rcv_ingredient,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Ingredient_Adapter.ViewHolder holder, int position) {

        holder.ign_name.setText(ingredient_modelArrayList.get(position).getIngredient());
        holder.ign_measure.setText(ingredient_modelArrayList.get(position).getIngredient_measure());

//        holder.item_ins.setText(ingredient_modelArrayList.get(position).getItem_inst());



    }

    @Override
    public int getItemCount() {
        return ingredient_modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ign_name, ign_measure, item_ins;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ign_measure = itemView.findViewById(R.id.ign_measure);
            ign_name = itemView.findViewById(R.id.ign_name);
//            item_ins = itemView.findViewById(R.id.meal_desc);


        }
    }
}
