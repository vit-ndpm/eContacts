package com.degs.econtacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    ImageView lougout,toolbarLogoImageView;
    TextView toolbarTextView;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

       // toolbarLogoImageView.setImageURI(currentFirebaseUser.getUid());
        lougout=findViewById(R.id.logout_img_home);
        toolbarTextView=findViewById(R.id.toolbar_txt_home);
       // toolbarLogoImageView.setImageURI(currentFirebaseUser.getPhotoUrl());
        toolbarTextView.setText(currentFirebaseUser.getEmail());
        toolbarLogoImageView=findViewById(R.id.logo_image_home);
        lougout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                sendUserToLoginActivity();
                finish();

            }
        });


    }
    // function to the button on press
    private void sendUserToLoginActivity() {
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);

    }
}