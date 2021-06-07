package com.example.shareyourfoodapp.controller.bll;

import android.content.Context;
import com.example.shareyourfoodapp.controller.dal.CommentDal;
import com.example.shareyourfoodapp.model.Comment;
import java.util.ArrayList;

public class CommentBll {

    public static ArrayList<Comment> getComments(Context context, Integer idRecipe){
        return CommentDal.getComments(context, idRecipe);
    }

    public static Boolean addComment(Context context, Integer idRecipe, String text) {
        return CommentDal.addComment(context, idRecipe, text);
    }

}
