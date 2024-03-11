package com.degs.econtacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button login, register;
    private FirebaseAuth mAuth;//Used for firebase authentication

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            sendUserToHomeActivity(mAuth.getCurrentUser());

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mAuth.getCurrentUser() != null) {
            sendUserToHomeActivity(mAuth.getCurrentUser());

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login_btn);
        register = findViewById(R.id.register_btn);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            sendUserToHomeActivity(mAuth.getCurrentUser());

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    sendUserToHomeActivity(mAuth.getCurrentUser());

                }
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    sendUserToHomeActivity(mAuth.getCurrentUser());

                }
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });
    }

    private void sendUserToHomeActivity(FirebaseUser user) {
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
    }
}