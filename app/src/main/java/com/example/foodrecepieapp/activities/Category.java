package com.example.foodrecepieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.foodrecepieapp.R;
import com.example.foodrecepieapp.adapter.CategoryAdapter;
import com.example.foodrecepieapp.model.Category_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Category extends AppCompatActivity {

    RecyclerView cat_recycle_view;
    TextView desc, meal_title;
    String title;
    ImageView title_img;
    ArrayList<Category_model> meal_arrayList = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Objects.requireNonNull(getSupportActionBar()).hide();

        desc = findViewById(R.id.description);
        cat_recycle_view = findViewById(R.id.cat_recv);
        title_img = findViewById(R.id.title_img);
        meal_title = findViewById(R.id.meal_title);


        desc.setMovementMethod(new ScrollingMovementMethod()); // making text view scrollable

//         setting the description and image form the category;
        Intent intent = getIntent();
        Glide.with(Category.this).load(intent.getStringExtra("imgUrl")).into(title_img);
        desc.setText(intent.getStringExtra("desc"));
        title = intent.getStringExtra("title");
        meal_title.setText(title);


        // setting recycle view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        cat_recycle_view.setLayoutManager(gridLayoutManager);

        item_by_catergories(title);



    }

    public void item_by_catergories(String end_title){
        String cat_url = "https://www.themealdb.com/api/json/v1/1/filter.php?c="+end_title;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, cat_url, null, response -> {

                    try {
                        JSONArray array_meals = response.getJSONArray("meals");

                        for(int i =0;i<array_meals.length();i++){

                            JSONObject final_meal_obj = array_meals.getJSONObject(i);
                            Log.d("final", final_meal_obj.toString());

                            meal_arrayList.add(new Category_model(final_meal_obj.getString("idMeal"),
                                    final_meal_obj.getString("strMealThumb"),
                                    final_meal_obj.getString("strMeal")));
                        }// loop end
                         categoryAdapter = new CategoryAdapter(meal_arrayList,getApplicationContext());
                        cat_recycle_view.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(jsonObjectRequest);


    }
}