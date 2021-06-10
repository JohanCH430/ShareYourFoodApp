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

public class RecipeSearch extends AppCompatActivity {

    private ListView recipeList;
    private ArrayList<Recipe> mRecipes;

    String txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);

        txtSearch = getIntent().getStringExtra("txtSearch");

        recipeList = findViewById(R.id.recipeList);
        new GetRecipeSearchTask(txtSearch).execute();

    }

    private class GetRecipeSearchTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;
        String txt;

        public GetRecipeSearchTask(String txt) {
            this.txt = txt;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(RecipeSearch.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                mRecipes = RecipeBll.getRecipeSearch(RecipeSearch.this, txt);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                AdapterRecipes adapter = new AdapterRecipes(RecipeSearch.this, mRecipes);
                recipeList.setAdapter(adapter);
            }
            else
                Toast.makeText(RecipeSearch.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }


}