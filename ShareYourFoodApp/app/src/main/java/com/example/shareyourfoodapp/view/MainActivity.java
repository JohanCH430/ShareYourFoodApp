package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.AccountBll;
import com.example.shareyourfoodapp.controller.bll.RecipeBll;
import com.example.shareyourfoodapp.model.Recipe;
import com.google.android.material.navigation.NavigationView;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private ListView recipeList;
    private ArrayList<Recipe> mRecipes;

    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);
        toolbar.setTitle(getString(R.string.app_name));

        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        name = navigationView.getHeaderView(0).findViewById(R.id.name);
        email = navigationView.getHeaderView(0).findViewById(R.id.email);

        recipeList = findViewById(R.id.recipeList);
        new GetRecipesTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText(AccountBll.getNameUser());
        email.setText(AccountBll.getEmailUser());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_account:
                startActivity(new Intent(this, MyAccount.class));
                break;

            case R.id.nav_my_recipes:
                startActivity(new Intent(this, MyRecipes.class));
                break;

            case R.id.nav_favorites:
                startActivity(new Intent(this, Favorites.class));
                break;

            case R.id.nav_logOut:
                AccountBll.logOut();
                Intent i = new Intent(MainActivity.this, Splash.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnSearch) {
            recipeSearch();
        }
        return super.onOptionsItemSelected(item);
    }

    private void recipeSearch() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.ad_recipe_search, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Buscar receta");
        alertDialogBuilder.setView(promptsView);

        EditText txtSearch = promptsView.findViewById(R.id.txtSearch);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Buscar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtSearch.getText().toString().trim();
                if(text.equals("")){
                    txtSearch.setError("Introduce el nombre de la receta");
                }else{
                    alertDialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, RecipeSearch.class);
                    intent.putExtra("txtSearch", text);
                    startActivity(intent);
                }
            }
        });
    }


    private class GetRecipesTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;

        public GetRecipesTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                mRecipes = RecipeBll.getRecipes(MainActivity.this);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                AdapterRecipes adapter = new AdapterRecipes(MainActivity.this, mRecipes);
                recipeList.setAdapter(adapter);
            }
            else
                Toast.makeText(MainActivity.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }

}