package com.degs.econtacts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Role extends AppCompatActivity {
    ArrayList<Role_Model> roleModelArrayList;
    RecyclerView recyclerView;
    SearchView searchView_post;
    Role_RC_Adapter adapter;
    private ProgressDialog progressDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_role);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView_post = findViewById(R.id.searchview_toolbar);
        recyclerView = findViewById(R.id.recyclerview_role);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        roleModelArrayList = new ArrayList<>();
        progressDialogue = new ProgressDialog(this);

        //Search functionality start here
        searchView_post.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        // Search functionality end here
        progressDialogue.setTitle("Loading Department");
        progressDialogue.setMessage("Lodaing Department please Wait...");
        progressDialogue.show();
        fillRoles();
    }

    public void filterList(String text) {
        ArrayList<Role_Model> filteredList = new ArrayList<>();
        for (Role_Model roleModel : roleModelArrayList) {
            if (roleModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || roleModel.name_eng.toLowerCase().contains(text.toLowerCase())

                    || roleModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || roleModel.short_name_eng.toLowerCase().contains(text.toLowerCase())
                    || roleModel.short_name_hi.toLowerCase().contains(text.toLowerCase())
                    || roleModel.short_name_eng.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(roleModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillRoles() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allRoles").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject role = response.getJSONObject(i);
                        int id = role.getInt("id");
                        int rank = role.getInt("rank");
                        String name_eng = role.getString("name_eng");
                        String name_hi = role.getString("name_hi");
                        String short_name_eng = role.getString("short_name_eng");
                        String short_name_hi = role.getString("short_name_hi");
                        Role_Model roleModel = new Role_Model(id, rank, name_eng, name_hi, short_name_eng, short_name_hi);
                        roleModelArrayList.add(roleModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Role_RC_Adapter(Role.this, roleModelArrayList);
                recyclerView.setAdapter(adapter);
                progressDialogue.setTitle("Loading Department");
                progressDialogue.setMessage("Lodaing Department please Wait...");
                progressDialogue.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Role.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialogue.dismiss();
            }
        });


    }
}