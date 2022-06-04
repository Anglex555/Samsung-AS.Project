package com.anglex.shop_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anglex.shop_online.controller.AuthController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {

    EditText r_username, r_email, r_password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(1500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        AuthController authController = new AuthController();
        if(AuthController.isRegistered()) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }

        setContentView(R.layout.activity_register);

        r_username = findViewById(R.id.r_username);
        r_email = findViewById(R.id.r_email);
        r_password = findViewById(R.id.r_password);
        button = findViewById(R.id.r_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = r_username.getText().toString();
                String email = r_email.getText().toString();
                String password = r_password.getText().toString();

                authController.registerNewUser(email, password, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            Toast.makeText(RegisterActivity.this, authController.getUser().getEmail(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Проверьте корректность данных", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}