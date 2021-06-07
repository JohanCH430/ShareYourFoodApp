package com.example.shareyourfoodapp.controller.dal;

import android.content.Context;
import com.example.shareyourfoodapp.utils.MyDatabaseManager;

public class AccountDal {

    public static Boolean login(Context context, String email, String password){
        return MyDatabaseManager.login(MyDatabaseManager.myConnection(context), email, password);
    }

    public static Boolean register(Context context, String name, String email, String password){
        return MyDatabaseManager.register(MyDatabaseManager.myConnection(context), name, email, password);
    }

}


