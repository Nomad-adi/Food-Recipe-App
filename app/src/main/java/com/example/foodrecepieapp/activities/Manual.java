package com.example.foodrecepieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.foodrecepieapp.R;
import com.example.foodrecepieapp.adapter.Ingredient_Adapter;
import com.example.foodrecepieapp.adapter.ReceipeAdapter;
import com.example.foodrecepieapp.model.Ingredient_Model;
import com.example.foodrecepieapp.model.RecipieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Manual extends AppCompatActivity {

    ImageView manual_img;
    TextView manual_title, meal_ins;
    String meal_id;
    RecyclerView manual_rcv;
    Ingredient_Adapter ingredient_adapter;
    ArrayList<Ingredient_Model> ingredient_modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        Objects.requireNonNull(getSupportActionBar()).hide();

        manual_img = findViewById(R.id.manual_img);
        manual_title = findViewById(R.id.manual_title);
        manual_rcv = findViewById(R.id.manual_rcv);

        meal_ins = findViewById(R.id.meal_desc);

        Intent manual_intent = getIntent();
        Glide.with(Manual.this).load(manual_intent.getStringExtra("meal_url")).into(manual_img);
        manual_title.setText(manual_intent.getStringExtra("meal_title"));
        meal_id = manual_intent.getStringExtra("meal_id");

        // setting recv
        manual_rcv.setLayoutManager(new LinearLayoutManager(this));

        fetch_details(meal_id);
    }

    public void fetch_details(String meal_id){

        String url_id = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="+meal_id;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_id, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array_data_meal = response.getJSONArray("meals");

                                JSONObject final_meal_obj = array_data_meal.getJSONObject(0);
                                Log.d("final", final_meal_obj.toString());


                                String strIgn = "strIngredient1";

                                for(int i =1;!final_meal_obj.getString(strIgn).isEmpty();i++){

                                    strIgn = "strIngredient"+i;
                                    String strMeasure = "strMeasure"+i;
                                    Log.d("tag",strIgn);
                                    Ingredient_Model ignModelObj = new Ingredient_Model(final_meal_obj.getString(strIgn),
                                            final_meal_obj.getString(strMeasure),
                                            final_meal_obj.getString("strYoutube"));
                                    ingredient_modelArrayList.add(ignModelObj);

                                }
                                ingredient_modelArrayList.add(new Ingredient_Model(final_meal_obj.getString("strInstructions")));
                                meal_ins.setText(final_meal_obj.getString("strInstructions"));
//                                ingredient_modelArrayList.add(new Ingredient_Model(final_meal_obj.getString("strIngredient1"),
//                                        final_meal_obj.getString("strMeasure1"),
//                                        final_meal_obj.getString("strInstructions"),
//                                        final_meal_obj.getString("strYoutube")));


                            ingredient_adapter = new Ingredient_Adapter(getApplicationContext(),ingredient_modelArrayList);
                            manual_rcv.setAdapter(ingredient_adapter);
                            ingredient_adapter.notifyDataSetChanged();

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
}