package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.AccountBll;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        ((TextView) findViewById(R.id.txtName)).setText(AccountBll.getNameUser(MyAccount.this));
        ((TextView) findViewById(R.id.txtEmail)).setText(AccountBll.getEmailUser(MyAccount.this));
    }

}