package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.AccountBll;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private Button btnSignUp, btnSignIn;
    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().toLowerCase();
                String password = mPassword.getText().toString();
                if(!email.equals("") && !password.equals("")){
                    new LoginTask(email, password).execute();
                }
                else{
                    mPassword.setError("Introduzca los datos");
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });
    };


    private class LoginTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;
        private String email, password;
        private Boolean login;

        public LoginTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(SignIn.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                login = AccountBll.login(SignIn.this, email, password);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                if(login){
                    Intent i = new Intent(SignIn.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
                else{
                    mPassword.setError("Datos incorrectos");
                }
            }
            else
                Toast.makeText(SignIn.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }


}