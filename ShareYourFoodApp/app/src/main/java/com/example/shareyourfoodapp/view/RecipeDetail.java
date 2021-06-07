package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.CommentBll;
import com.example.shareyourfoodapp.model.Comment;
import com.example.shareyourfoodapp.model.Recipe;

import java.util.ArrayList;

public class RecipeDetail extends AppCompatActivity {

    ImageView img;
    TextView txtTitle, txtDescription;
    Button btnAddComment;
    NonScrollListView commentList;
    AdapterComments adapter;

    Recipe mRecipe;
    ArrayList<Comment> mComments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mRecipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        img = (ImageView) findViewById(R.id.img);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        btnAddComment = (Button) findViewById(R.id.btnAddComment);
        commentList = (NonScrollListView) findViewById(R.id.commentList);

        Glide.with(RecipeDetail.this)
                .load(mRecipe.getImage_url())
                .apply(new RequestOptions().placeholder(R.drawable.example_recipe).error(R.drawable.example_recipe))
                .into(img);

        txtTitle.setText(mRecipe.getTitle());
        txtDescription.setText(mRecipe.getDescription());
        new GetCommentsTask().execute();

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });

    }


    private void addComment() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.ad_add_comment, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Añadir opinión");
        alertDialogBuilder.setView(promptsView);

        EditText txtText = promptsView.findViewById(R.id.txtText);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Guardar",
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
                String text = txtText.getText().toString().trim();
                if(text.equals("")){
                    txtText.setError("El comentario no puede estar vacío ");
                }else{
                    alertDialog.dismiss();
                    new AddCommentsTask(text).execute();
                }
            }
        });
    }


    private class AddCommentsTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;
        String text;

        private Boolean added;

        public AddCommentsTask(String text) {
            this.text = text;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(RecipeDetail.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                added = CommentBll.addComment(RecipeDetail.this, mRecipe.getId(), text);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                if(added){
                    new GetCommentsTask().execute();
                }
                else{
                    Toast.makeText(RecipeDetail.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
                }

            }
            else
                Toast.makeText(RecipeDetail.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }

    private class GetCommentsTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;

        public GetCommentsTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(RecipeDetail.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                mComments = CommentBll.getComments(RecipeDetail.this, mRecipe.getId());
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {

                if(commentList.getAdapter() == null){
                    adapter = new AdapterComments(RecipeDetail.this);
                    commentList.setAdapter(adapter);
                }
                else{
                    adapter.notifyDataSetChanged();
                }

            }
            else
                Toast.makeText(RecipeDetail.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }


    static class CommentViewHolder {
        TextView txtAuthor, txtText;
    }

    class AdapterComments extends ArrayAdapter {

        public AdapterComments(Context context) {
            super(context, R.layout.adapter_comment_row);
        }

        @Override
        public int getCount() {
            return mComments.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            final CommentViewHolder holder;

            if (item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.adapter_comment_row, null);

                holder = new CommentViewHolder();
                holder.txtAuthor = item.findViewById(R.id.txtAuthor);
                holder.txtText = item.findViewById(R.id.txtText);
                item.setTag(holder);
            }
            else {
                holder = (CommentViewHolder) convertView.getTag();
            }

            Comment comment = mComments.get(position);
            holder.txtAuthor.setText(comment.getAuthor_mail());
            holder.txtText.setText(comment.getText());

            return (item);
        }
    }


}