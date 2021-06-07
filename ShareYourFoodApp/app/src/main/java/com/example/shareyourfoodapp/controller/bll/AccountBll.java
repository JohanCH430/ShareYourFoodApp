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

    public static String getNameUser(Context context){
        return AccountDal.getNameUser(context);
    }

    public static String getEmailUser(Context context){
        return AccountDal.getEmailUser(context);
    }

}
