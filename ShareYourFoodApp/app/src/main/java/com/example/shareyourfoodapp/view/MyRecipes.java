package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.RecipeBll;
import com.example.shareyourfoodapp.model.Recipe;
import java.util.ArrayList;

public class MyRecipes extends AppCompatActivity {

    private ListView recipeList;
    private ArrayList<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        recipeList = findViewById(R.id.recipeList);
        new GetMyRecipesTask().execute();

    }

    private class GetMyRecipesTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;

        public GetMyRecipesTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MyRecipes.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                mRecipes = RecipeBll.getMyRecipes(MyRecipes.this);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                AdapterRecipes adapter = new AdapterRecipes(MyRecipes.this, mRecipes);
                recipeList.setAdapter(adapter);
            }
            else
                Toast.makeText(MyRecipes.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }

}