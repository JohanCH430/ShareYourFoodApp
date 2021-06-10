package com.example.shareyourfoodapp.controller.bll;

import android.content.Context;
import com.example.shareyourfoodapp.controller.dal.RecipeDal;
import com.example.shareyourfoodapp.model.Recipe;
import java.util.ArrayList;

public class RecipeBll {

    public static ArrayList<Recipe> getRecipes(Context context){
        return RecipeDal.getRecipes(context);
    }

    public static ArrayList<Recipe> getMyRecipes(Context context){
        return RecipeDal.getMyRecipes(context);
    }

    public static ArrayList<Recipe> getRecipeSearch(Context context, String txt){
        return RecipeDal.getRecipeSearch(context, txt);
    }

}
