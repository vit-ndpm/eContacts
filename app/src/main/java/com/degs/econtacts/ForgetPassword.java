package com.degs.econtacts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    Button sendEmail;
    EditText email_et;
    String email;
    private ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        sendEmail = findViewById(R.id.send_email_btn_forget);
        email_et = findViewById(R.id.email_et_forget);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Reset Password Request");
                progressDialog.setMessage("Please wait, we are sending Password Reset email");
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();
                if (isValidEmail(email_et.getText().toString().trim())) {
                    email = email_et.getText().toString().trim();
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgetPassword.this, "Email Sent to Registred E-Mail ID Please Check your email", Toast.LENGTH_SHORT).show();

                                        progressDialog.dismiss();
                                    } else {
                                        Toast.makeText(ForgetPassword.this, "Email Sent Failed:" + task.getException(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });

                }
                else {
                    Toast.makeText(ForgetPassword.this, "Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }
        });


    }

    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }
}