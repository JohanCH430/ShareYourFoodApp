package com.example.shareyourfoodapp.controller.bll;

import android.content.Context;
import com.example.shareyourfoodapp.controller.dal.AccountDal;

public class AccountBll {

    public static Boolean login(Context context, String email, String password){
        return AccountDal.login(context, email, password);
    }

    public static Boolean register(Context context, String name, String email, String password){
        return AccountDal.register(context, name, email, password);
    }

}
