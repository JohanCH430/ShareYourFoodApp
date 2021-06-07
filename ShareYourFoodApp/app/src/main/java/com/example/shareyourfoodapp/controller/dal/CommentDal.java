package com.example.shareyourfoodapp.controller.dal;

import android.content.Context;
import com.example.shareyourfoodapp.model.Comment;
import com.example.shareyourfoodapp.utils.MyDatabaseManager;
import java.util.ArrayList;

public class CommentDal {

    public static ArrayList<Comment> getComments(Context context, Integer idRecipe){
        return MyDatabaseManager.getComments(MyDatabaseManager.myConnection(context), idRecipe);
    }

    public static Boolean addComment(Context context, Integer idRecipe, String text) {
        return MyDatabaseManager.addComment(MyDatabaseManager.myConnection(context), idRecipe, text);
    }

}
