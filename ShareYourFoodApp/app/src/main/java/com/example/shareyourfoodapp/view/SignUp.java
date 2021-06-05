package com.example.shareyourfoodapp.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shareyourfoodapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    private Button btnSignUp;
    private EditText email, password, repeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = ((EditText) findViewById (R.id.email));
        password = ((EditText) findViewById (R.id.password));
        repeatPassword = ((EditText) findViewById (R.id.repeatPassword));
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(repeatPassword.getText().toString())) {
                    if (true /* comprobar si ese usuario y contraseña existen*/) {
                        email.setError("This account already exists");
                    } else {
                        // Si no son datos repetidos se registra la nueva cuenta
                        //toast = Toast.makeText(getApplicationContext(), "user registered correctly", Toast.LENGTH_SHORT);
                        //toast.show();
                        //startActivity(new Intent(SignIn.this, InicioApp.class);
                    }
                } else
                    repeatPassword.setError("the passwords must be equals");
            }
        });
    }
}