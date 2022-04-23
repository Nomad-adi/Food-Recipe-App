package com.example.foodrecepieapp.model;

public class Category_model {

    String meal_id;
    String meal_url;
    String meal_title;

    public Category_model(String meal_id, String meal_url, String meal_title) {
        this.meal_id = meal_id;
        this.meal_url = meal_url;
        this.meal_title = meal_title;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(String meal_id) {
        this.meal_id = meal_id;
    }

    public String getMeal_url() {
        return meal_url;
    }

    public void setMeal_url(String meal_url) {
        this.meal_url = meal_url;
    }

    public String getMeal_title() {
        return meal_title;
    }

    public void setMeal_title(String meal_title) {
        this.meal_title = meal_title;
    }
}
