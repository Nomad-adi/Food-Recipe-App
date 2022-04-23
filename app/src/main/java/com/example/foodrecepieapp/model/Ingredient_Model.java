package com.example.foodrecepieapp.model;

import java.util.ArrayList;

public class Ingredient_Model {

    String ingredient, ingredient_measure;
    String item_inst;
    String yt_link;

    public Ingredient_Model(String item_inst){
            this.item_inst = item_inst;
    }

    public Ingredient_Model(String ingredient, String ingredient_measure, String yt_link) {
        this.ingredient = ingredient;
        this.ingredient_measure = ingredient_measure;
        this.yt_link = yt_link;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient_measure() {
        return ingredient_measure;
    }

    public void setIngredient_measure(String ingredient_measure) {
        this.ingredient_measure = ingredient_measure;
    }

    public String getItem_inst() {
        return item_inst;
    }

    public void setItem_inst(String item_inst) {
        this.item_inst = item_inst;
    }

    public String getYt_link() {
        return yt_link;
    }

    public void setYt_link(String yt_link) {
        this.yt_link = yt_link;
    }
}
