package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.AccountBll;
import com.example.shareyourfoodapp.controller.bll.RecipeBll;
import com.example.shareyourfoodapp.model.Recipe;
import com.google.android.material.navigation.NavigationView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private ListView recipeList;
    private ArrayList<Recipe> mRecipes;

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

        TextView name = navigationView.getHeaderView(0).findViewById(R.id.name);
        TextView email = navigationView.getHeaderView(0).findViewById(R.id.email);
        name.setText(AccountBll.getNameUser(MainActivity.this));
        email.setText(AccountBll.getEmailUser(MainActivity.this));

        recipeList = findViewById(R.id.recipeList);
        new GetRecipesTask().execute();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_account:
                startActivity(new Intent(this, MyAccount.class));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                AdapterRecipes adapter = new AdapterRecipes(MainActivity.this);
                recipeList.setAdapter(adapter);
            }
            else
                Toast.makeText(MainActivity.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }



    static class ViewHolder {
        CardView cardView;
        TextView txtTitle;
        ImageView img;
    }

    class AdapterRecipes extends ArrayAdapter {

        public AdapterRecipes(Context context) {
            super(context, R.layout.adapter_recipe_row);
        }

        @Override
        public int getCount() {
            return mRecipes.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            final ViewHolder holder;

            if (item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.adapter_recipe_row, null);

                holder = new ViewHolder();
                holder.cardView = item.findViewById(R.id.cardView);
                holder.img = item.findViewById(R.id.img);
                holder.txtTitle = item.findViewById(R.id.txtTitle);

                holder.img.setImageDrawable(null);
                item.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.img.setImageDrawable(null);
            Recipe recipe = mRecipes.get(position);

            holder.txtTitle.setText(recipe.getTitle());
            holder.cardView.setTag(position);

            Glide.with(MainActivity.this)
                    .load(recipe.getImage_url())
                    .apply(new RequestOptions().placeholder(R.drawable.example_recipe).error(R.drawable.example_recipe))
                    .into(holder.img);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    Recipe r = mRecipes.get(pos);
                    Intent i = new Intent(MainActivity.this, RecipeDetail.class);
                    i.putExtra("Recipe", r);
                    startActivity(i);
                }
            });

            return (item);
        }
    }


}