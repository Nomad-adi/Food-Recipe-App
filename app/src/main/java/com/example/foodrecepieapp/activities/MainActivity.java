package com.example.foodrecepieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodrecepieapp.R;
import com.example.foodrecepieapp.adapter.CategoryAdapter;
import com.example.foodrecepieapp.adapter.ReceipeAdapter;
import com.example.foodrecepieapp.model.Category_model;
import com.example.foodrecepieapp.model.RecipieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver = null;

//    private String mainUrl = "https://www.themealdb.com/api/json/v1/1/";
    RecyclerView recyclerView, latest_rcv;

    ArrayList<RecipieModel> recipieModelList = new ArrayList<>();
    ArrayList<Category_model> category_modelArrayList = new ArrayList<>();
    ReceipeAdapter receipeAdapter;
    CategoryAdapter categoryAdapter;
    private LinearLayoutManager HorizontalLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // no internet dialog box setting
//        broadcastReceiver = new InternetCheckService();
//        checkInternet();

        setTitle("Food Recipe");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainColour)));



        recyclerView = findViewById(R.id.mainrcv);
        latest_rcv = findViewById(R.id.latest_rcv);

        // for latest meal
        HorizontalLayout = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        latest_rcv.setLayoutManager(HorizontalLayout);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        fetch_categories();

        for(int i =1;i<20;i++){
            fetch_latest_meal();
        }



    }

//    private void checkInternet() {
//
//        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(broadcastReceiver);
//    }

    public void fetch_categories(){

        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array_data = response.getJSONArray("categories");

                            for(int i =0;i<array_data.length();i++){

                                JSONObject finalobj = array_data.getJSONObject(i);
                                Log.d("final", finalobj.toString());
                                recipieModelList.add(new RecipieModel(finalobj.getString("idCategory"),
                                        finalobj.getString("strCategoryThumb"),
                                        finalobj.getString("strCategory"),
                                        finalobj.getString("strCategoryDescription")
                                ));
                            }// loop end
                            receipeAdapter = new ReceipeAdapter(getApplicationContext(), recipieModelList);
                            recyclerView.setAdapter(receipeAdapter);
                            receipeAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    public void fetch_latest_meal(){

        String cat_url = "https://www.themealdb.com/api/json/v1/1/random.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, cat_url, null, response -> {

                    try {
                        JSONArray array_meals = response.getJSONArray("meals");


                            JSONObject final_meal_obj = array_meals.getJSONObject(0);
                            Log.d("final", final_meal_obj.toString());

                            category_modelArrayList.add(new Category_model(final_meal_obj.getString("idMeal"),
                                    final_meal_obj.getString("strMealThumb"),
                                    final_meal_obj.getString("strMeal")));


                        categoryAdapter = new CategoryAdapter(category_modelArrayList,getApplicationContext());
                        latest_rcv.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
//                        Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(jsonObjectRequest);


    }


}