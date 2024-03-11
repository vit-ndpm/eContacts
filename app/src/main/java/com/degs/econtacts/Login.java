package com.degs.econtacts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    Button login_btn,register_btn,forget_password;
    EditText email_et,password_et;
    String email,password;
    private FirebaseAuth mAuth;//Used for firebase authentication
    private FirebaseDatabase firebaseDatabas;

    private ProgressDialog loadingBar;//Used to show the progress of the registration process
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
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabas=FirebaseDatabase.getInstance();
        loadingBar = new ProgressDialog(this);
        email_et=findViewById(R.id.email_et_login);
        password_et=findViewById(R.id.password_et_login);
        login_btn=findViewById(R.id.login_btn_login);
        forget_password=findViewById(R.id.forget_password_login);
        if (mAuth.getCurrentUser()!=null){
            sendUserToHomeActivity(mAuth.getCurrentUser());
        }
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        register_btn=findViewById(R.id.register_btn_login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()){
                    loadingBar.setTitle("Creating New Account");
                    loadingBar.setMessage("Please wait, we are creating new Account");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                sendUserToHomeActivity(user);
                                loadingBar.dismiss();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login.this, "Authentication failed."+task.getException(),
                                        Toast.LENGTH_SHORT).show();
                               loadingBar.dismiss();
                            }

                        }
                    });

                }

            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });
    }

    private void sendUserToRegisterActivity() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
    private void sendUserToHomeActivity(FirebaseUser user) {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }
    private boolean isValidData() {

        if (isValidEmail(email_et.getText().toString().trim())) {
            email = email_et.getText().toString().trim();
        } else {
            Toast.makeText(Login.this, "Enter Valid Email Address", Toast.LENGTH_SHORT).show();
            return false;

        }


        if (!TextUtils.isEmpty(password_et.getText().toString().trim())) {
            password = password_et.getText().toString().trim();
        } else {
            Toast.makeText(Login.this, "Either Password ", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }
}