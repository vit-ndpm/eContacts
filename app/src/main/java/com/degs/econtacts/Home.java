package com.degs.econtacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ImageView lougout, toolbarLogoImageView;
    TextView toolbarTextView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser currentFirebaseUser;
    RecyclerView mainItemRCV;
    ArrayList<HomeItemModel> homeItemModelArrayList;

    @Override
    protected void onStart() {
        super.onStart();
        if (currentFirebaseUser == null) {
            sendUserToLoginActivity();

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (currentFirebaseUser == null) {
            sendUserToLoginActivity();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Setup Toolbar
         currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        lougout = findViewById(R.id.logout_img_home);
        toolbarTextView = findViewById(R.id.toolbar_txt_home);
        // toolbarLogoImageView.setImageURI(currentFirebaseUser.getPhotoUrl());
        toolbarTextView.setText(currentFirebaseUser.getEmail());
        toolbarLogoImageView = findViewById(R.id.logo_image_home);
        lougout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                sendUserToLoginActivity();
                finish();

            }
        });
//setup recyclerview
        mainItemRCV = findViewById(R.id.main_item_recycler_View);
        mainItemRCV.setLayoutManager(new GridLayoutManager(this, 2));
        homeItemModelArrayList = new ArrayList<>();
        homeItemModelArrayList.add(new HomeItemModel(1, "RO Office", R.drawable.deooffice));
        homeItemModelArrayList.add(new HomeItemModel(2, "ARO Office", R.drawable.rooffice));
        homeItemModelArrayList.add(new HomeItemModel(3, "Nodal Officer", R.drawable.nodalofficer));
        homeItemModelArrayList.add(new HomeItemModel(4, "Sector Officer", R.drawable.sector));
        homeItemModelArrayList.add(new HomeItemModel(5, "Roles", R.drawable.role));
        homeItemModelArrayList.add(new HomeItemModel(6, "Departments", R.drawable.department));
        homeItemModelArrayList.add(new HomeItemModel(7, "Posts", R.drawable.baseline_local_police_24));
        homeItemModelArrayList.add(new HomeItemModel(8, "Polling Booths", R.drawable.booth));
        homeItemModelArrayList.add(new HomeItemModel(9, "Polling Party", R.drawable.pollingparty));
        homeItemModelArrayList.add(new HomeItemModel(10, "Control Rooms", R.drawable.controlroom));
        homeItemModelArrayList.add(new HomeItemModel(11, "FSTs", R.drawable.fst));
        homeItemModelArrayList.add(new HomeItemModel(12, "SSTs", R.drawable.sst));
        homeItemModelArrayList.add(new HomeItemModel(13, "VSTs", R.drawable.vst));
        homeItemModelArrayList.add(new HomeItemModel(14, "MCMC", R.drawable.mcmc));
        homeItemModelArrayList.add(new HomeItemModel(15, "Police Stations", R.drawable.policestations));
        homeItemModelArrayList.add(new HomeItemModel(16, "Important Links", R.drawable.link));
        homeItemModelArrayList.add(new HomeItemModel(17, "Notifications", R.drawable.notification));
        homeItemModelArrayList.add(new HomeItemModel(18, "Other", R.drawable.role));
        homeItemModelArrayList.add(new HomeItemModel(19, "Other", R.drawable.role));

        HomeItemAdapter adapter = new HomeItemAdapter(homeItemModelArrayList, this);
        mainItemRCV.setAdapter(adapter);


    }

    // function to the button on press
    private void sendUserToLoginActivity() {
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);

    }
}