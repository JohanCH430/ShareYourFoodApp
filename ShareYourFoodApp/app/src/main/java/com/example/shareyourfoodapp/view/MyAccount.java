package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shareyourfoodapp.R;
import com.example.shareyourfoodapp.controller.bll.AccountBll;

public class MyAccount extends AppCompatActivity {

    private TextView txtName;
    private EditText txtPassword, txtNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        txtName = (TextView) findViewById(R.id.txtName);
        Button btnChangeName = (Button) findViewById(R.id.btnChangeName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        Button btnChangePassword = (Button) findViewById(R.id.btnChangePassword);

        txtName.setText(AccountBll.getNameUser());

        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeName();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = txtPassword.getText().toString().trim();
                String newPassword = txtNewPassword.getText().toString().trim();

                if(password.equals("")){
                    txtPassword.setError("Debes introducir tu contraseña");
                }
                else if(!password.equals(AccountBll.getPasswordUser())){
                    txtPassword.setError("Contraseña incorrecta");
                }
                else if(newPassword.equals("")){
                    txtNewPassword.setError("Debes introducir una nueva contraseña");
                }
                else{
                    new ChangePasswordTask(password, newPassword).execute();
                }
            }
        });


    }


    private void changeName() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.ad_change_name, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Cambiar nombre");
        alertDialogBuilder.setView(promptsView);

        EditText txtNewName = promptsView.findViewById(R.id.txtNewName);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Guardar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtNewName.getText().toString().trim();
                if(text.equals("")){
                    txtNewName.setError("El nombre no puede estar vacío ");
                }else{
                    alertDialog.dismiss();
                    new ChangeNameTask(text).execute();
                }
            }
        });
    }

    private class ChangeNameTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;
        String name;

        private Boolean changed;

        public ChangeNameTask(String name) {
            this.name = name;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MyAccount.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                changed = AccountBll.changeName(MyAccount.this, name);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                if(changed){
                    txtName.setText(name);
                }
                else{
                    Toast.makeText(MyAccount.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(MyAccount.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }

    private class ChangePasswordTask extends AsyncTask<Void, Void, Exception> {
        ProgressDialog dialog;
        String password, newPassword;

        private Boolean changed;

        public ChangePasswordTask(String password, String newPassword) {
            this.password = password;
            this.newPassword = newPassword;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MyAccount.this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setMessage("Cargando...");
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                changed = AccountBll.changePassword(MyAccount.this, password, newPassword);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Exception ex) {
            dialog.dismiss();
            if (ex == null) {
                if(changed){
                    Toast.makeText(MyAccount.this, "Cambio de contraseña realizado correctamente", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MyAccount.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(MyAccount.this, "Ha ocurrido un error...", Toast.LENGTH_SHORT).show();
        }
    }

}