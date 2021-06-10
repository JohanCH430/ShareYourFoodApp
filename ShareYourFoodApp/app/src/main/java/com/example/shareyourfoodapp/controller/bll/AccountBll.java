package com.example.shareyourfoodapp.controller.bll;

import android.content.Context;
import com.example.shareyourfoodapp.controller.dal.AccountDal;

public class AccountBll {

    public static Boolean login(Context context, String email, String password){
        return AccountDal.login(context, email, password);
    }

    public static void logOut(){
        AccountDal.logOut();
    }

    public static Boolean register(Context context, String name, String email, String password){
        return AccountDal.register(context, name, email, password);
    }

    public static String getNameUser(){
        return AccountDal.getNameUser();
    }

    public static String getEmailUser(){
        return AccountDal.getEmailUser();
    }

    public static String getPasswordUser(){
        return AccountDal.getPasswordUser();
    }

    public static Boolean changeName(Context context, String newName){
        return AccountDal.changeName(context, newName);
    }

    public static Boolean changePassword(Context context, String password, String newPassword) {
        return AccountDal.changePassword(context, password, newPassword);
    }

}
