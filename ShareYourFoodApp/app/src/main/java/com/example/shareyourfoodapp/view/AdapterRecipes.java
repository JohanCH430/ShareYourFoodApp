package com.example.shareyourfoodapp.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.model.Recipe;
import java.util.ArrayList;

class AdapterRecipes extends ArrayAdapter {

    private ArrayList<Recipe> mRecipes;
    private Context mContext;

    static class ViewHolder {
        CardView cardView;
        TextView txtTitle;
        ImageView img;
    }

    public AdapterRecipes(Context context, ArrayList<Recipe> recipes) {
        super(context, R.layout.adapter_recipe_row);
        mContext = context;
        mRecipes = recipes;
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

        Glide.with(mContext)
                .load(recipe.getImage_url())
                .apply(new RequestOptions().placeholder(R.drawable.empty_image).error(R.drawable.empty_image))
                .into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Recipe r = mRecipes.get(pos);
                Intent i = new Intent(mContext, RecipeDetail.class);
                i.putExtra("Recipe", r);
                mContext.startActivity(i);
            }
        });

        return (item);
    }
}
