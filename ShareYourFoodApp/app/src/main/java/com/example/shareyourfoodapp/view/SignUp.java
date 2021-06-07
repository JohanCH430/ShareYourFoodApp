package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.AccountBll;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private Button btnSignUp;
    private EditText txtName, txtEmail, txtPassword, txtRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtName = ((EditText) findViewById (R.id.txtName));
        txtEmail = ((EditText) findViewById (R.id.txtEmail));
        txtPassword = ((EditText) findViewById (R.id.txtPassword));
        txtRepeatPassword = ((EditText) findViewById (R.id.txtRepeatPassword));
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, password, repeatPassword;
                name = txtName.getText().toString();
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();
                repeatPassword = txtRepeatPassword.getText().toString();

                if(name.equals("")){
                    txtName.setError("Introduzca el nombre");
                    return;
                }
                else if(email.equals("")){
                    txtEmail.setError("Introduzca el email");
                }
                else if(!password.equals(repeatPassword)){
                    txtRepeatPassword.setError("Las contraseñas no coinciden");
                }
                else{
                    new RegisterTask(name, email, password).execute();
                }

            }
        });
    }


    private class RegisterTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;
        private String name, email, password;
        private Boolean register;

        public RegisterTask(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(SignUp.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                register = AccountBll.register(SignUp.this, name, email, password);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                if(register){
                    //MenuPrincipal
                    //startActivity(new Intent(SignIn.this, SignUp.class));
                    System.out.println("Datos correctos");
                }
                else{
                    Toast.makeText(SignUp.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(SignUp.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }

    }


}