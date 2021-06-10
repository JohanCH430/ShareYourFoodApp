package com.example.shareyourfoodapp.controller.dal;

import android.content.Context;
import com.example.shareyourfoodapp.utils.MyDatabaseManager;

public class AccountDal {

    public static Boolean login(Context context, String email, String password){
        return MyDatabaseManager.login(MyDatabaseManager.myConnection(context), email, password);
    }

    public static void logOut(){
        MyDatabaseManager.logOut();
    }

    public static Boolean register(Context context, String name, String email, String password){
        return MyDatabaseManager.register(MyDatabaseManager.myConnection(context), name, email, password);
    }

    public static String getNameUser(){
        return MyDatabaseManager.mUser.getName();
    }

    public static String getEmailUser(){
        return MyDatabaseManager.mUser.getEmail();
    }

    public static String getPasswordUser(){
        return MyDatabaseManager.mUser.getPassword();
    }


    public static Boolean changeName(Context context, String newName){
        return MyDatabaseManager.changeName(MyDatabaseManager.myConnection(context), newName);
    }

    public static Boolean changePassword(Context context, String password, String newPassword) {
        return MyDatabaseManager.changePassword(MyDatabaseManager.myConnection(context), password, newPassword);
    }


}


