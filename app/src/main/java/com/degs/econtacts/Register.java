package com.degs.econtacts;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    Button submit, login;
    ImageView userImage, select_img_register;
    EditText user_name_et, email_et, mobile_et, password_et, password_confirmation_et;
    String user_name, email, mobile, password, password_confirmation;
    DatabaseReference databaseReference;
    Uri imagePath;
    Bitmap bitmap;
    String user_id;
    private FirebaseAuth mAuth;//Used for firebase authentication
    private FirebaseDatabase firebaseDatabase;
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
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        loadingBar = new ProgressDialog(this);
        submit = findViewById(R.id.submit_btn);
        login = findViewById(R.id.login_reg_btn);
        userImage = findViewById(R.id.user_image);
        select_img_register = findViewById(R.id.select_img_register);
        if (mAuth.getCurrentUser()!=null){
            sendUserToHomeActivity(mAuth.getCurrentUser());
        }
        select_img_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(Register.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 101);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        user_name_et = findViewById(R.id.user_name);
        email_et = findViewById(R.id.user_email);
        mobile_et = findViewById(R.id.user_mobile);
        password_et = findViewById(R.id.password);
        password_confirmation_et = findViewById(R.id.password_confirmation);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidData()) {
                    // Toast.makeText(Register.this, "Valiation Passed", Toast.LENGTH_SHORT).show();
                    loadingBar.setTitle("Creating New Account");
                    loadingBar.setMessage("Please wait, we are creating new Account");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())//If account creation successful print message and send user to Login Activity
                            {
                                FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (mCurrentUser != null) {
                                    user_id = mCurrentUser.getUid();
                                }
                                uploadImage();
                                sendUserToLoginActivity();
                                Toast.makeText(Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else//Print the error message incase of failure
                            {
                                String msg = task.getException().toString();
                                Toast.makeText(Register.this, "Error: " + msg, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });

                }


            }
        });

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 101 && resultCode == RESULT_OK) {

                imagePath = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(imagePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                userImage.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void uploadImage() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploader");
        progressDialog.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference().child("image" + System.currentTimeMillis());
        reference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String fileLink = task.getResult().toString();
                        databaseReference = firebaseDatabase.getReference("users");
                        UserModel userModel = new UserModel(user_name, email, mobile, fileLink);
                        databaseReference.child(user_id).setValue(userModel);
                    }
                });

                progressDialog.dismiss();
                Toast.makeText(Register.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent = snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded:" + snapshot.getBytesTransferred() + "out of " + snapshot.getTotalByteCount());
            }
        });

    }

    private void sendUserToLoginActivity() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }

    private boolean isValidData() {
        if (!TextUtils.isEmpty(user_name_et.getText().toString().trim())) {
            user_name = user_name_et.getText().toString().trim();
        } else {
            Toast.makeText(Register.this, "Enter User Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isValidEmail(email_et.getText().toString().trim())) {
            email = email_et.getText().toString().trim();
        } else {
            Toast.makeText(Register.this, "Enter Valid Email Address", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (!TextUtils.isEmpty(mobile_et.getText().toString().trim())) {
            if (isValidMobile(mobile_et.getText().toString().trim())) {
                mobile = mobile_et.getText().toString().trim();
            } else {
                Toast.makeText(Register.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(Register.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();

            return false;
        }

        if (!TextUtils.isEmpty(password_et.getText().toString().trim())
                && !TextUtils.isEmpty(password_confirmation_et.getText().toString().trim())
                && password_et.getText().toString().trim().equals(password_confirmation_et.getText().toString().trim())) {
            password = password_et.getText().toString().trim();
        } else {
            Toast.makeText(Register.this, "Either Password or Confirm Password is Empty or Not Matching", Toast.LENGTH_SHORT).show();
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
    private void sendUserToHomeActivity(FirebaseUser user) {
        Intent intent = new Intent(Register.this, Home.class);
        startActivity(intent);
    }
}