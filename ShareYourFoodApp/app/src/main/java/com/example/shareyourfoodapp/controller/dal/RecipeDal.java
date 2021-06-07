package com.example.shareyourfoodapp.controller.dal;

import android.content.Context;
import com.example.shareyourfoodapp.model.Recipe;
import com.example.shareyourfoodapp.utils.MyDatabaseManager;
import java.util.ArrayList;

public class RecipeDal {

    public static ArrayList<Recipe> getRecipes(Context context){
        return MyDatabaseManager.getRecipes(MyDatabaseManager.myConnection(context));
    }
}
