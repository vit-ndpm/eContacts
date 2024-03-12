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

public class RO_Office extends AppCompatActivity {
    ArrayList<RO_Office_Model> roOfficeModelArrayList;
    RO_Office_RC_Adapter roOfficeRcAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ro_office);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        roOfficeModelArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview_ro_office);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog=new ProgressDialog(this);
        searchView=findViewById(R.id.searchview_toolbar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        progressDialog.setTitle("Officers");
        progressDialog.setMessage("Loading Officers..");
        progressDialog.show();
        fillOfficers();

    }
    public void filterList(String text) {
        ArrayList<RO_Office_Model> filteredList = new ArrayList<>();
        for (RO_Office_Model roOfficeModel : roOfficeModelArrayList) {
            if (roOfficeModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || roOfficeModel.name_eng.toLowerCase().contains(text.toLowerCase())

                    || roOfficeModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || roOfficeModel.mobile.toLowerCase().contains(text.toLowerCase())
                    || roOfficeModel.email.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(roOfficeModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                roOfficeRcAdapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillOfficers() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/roOffice").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject post = response.getJSONObject(i);
                        int id = post.getInt("id");
                        int rank = post.getInt("rank");
                        String name_eng = post.getString("name_eng");
                        String name_hi = post.getString("name_hi");
                        String mobile = post.getString("mobile");
                        String email = post.getString("email");

                        RO_Office_Model roOfficeModel = new RO_Office_Model(id,name_eng,name_hi,mobile,email);
                        roOfficeModelArrayList.add(roOfficeModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                roOfficeRcAdapter = new RO_Office_RC_Adapter(RO_Office.this, roOfficeModelArrayList);
                recyclerView.setAdapter(roOfficeRcAdapter);
                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(RO_Office.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });


    }
}