package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.model.Recipe;

public class RecipeDetail extends AppCompatActivity {

    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mRecipe = (Recipe) getIntent().getSerializableExtra("Recipe");
    }
}