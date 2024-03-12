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
import com.androidnetworking.model.Progress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sst extends AppCompatActivity {
ArrayList<Sst_Model>sstModelArrayList;
Sst_RC_Adapter adapter;
RecyclerView recyclerView;
ProgressDialog progressDialog;
SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sst);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sstModelArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview_sst);
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
        progressDialog.setTitle("SST Loader");
        progressDialog.setMessage("Loading SSTs ....");
        progressDialog.show();
        fillSsts();
    }
    public void filterList(String text) {
        ArrayList<Sst_Model> filteredList = new ArrayList<>();
        for (Sst_Model sst_model : sstModelArrayList) {
            if (sst_model.name_eng.toLowerCase().contains(text.toLowerCase())
                    || sst_model.name_eng.toLowerCase().contains(text.toLowerCase())

                    || sst_model.name_hi.toLowerCase().contains(text.toLowerCase())
                    || sst_model.assembly_name_eng.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sst_model);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillSsts() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allSsts").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response));
//                ArrayList<Officer_Model>officersList;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject sst = response.getJSONObject(i);
                        int id = sst.getInt("id");
                        int assembly_id = sst.getInt("assembly_id");
                        String name_eng = sst.getString("name_eng");
                        String name_hi = sst.getString("name_hi");
                        String shift = sst.getString("shift");
                        String assembly_name_eng = sst.getJSONObject("assembly").getString("name_eng");
                        String assembly_name_hi = sst.getJSONObject("assembly").getString("name_hi");
                       JSONArray officers=sst.getJSONArray("officers");
                       ArrayList<Officer_Model>officersList=new ArrayList<>();
//                       if (officers.length()>1){
//                           for (int j = 0; j <officers.length() ; j++) {
//                               JSONObject officer = response.getJSONObject(j);
//                               int officer_id = officer.getInt("id");
//                               int officer_rank = officer.getInt("rank");
//                               String officer_name_eng = officer.getString("name_eng");
//                               String officer_name_hi = officer.getString("name_hi");
//                               String officer_mobile = officer.getString("mobile");
//                               String officer_email = officer.getString("email");
//                               officersList.add(new Officer_Model(officer_id,officer_rank,officer_name_eng,officer_name_hi,officer_mobile,officer_email));
//
//                           }
//                       }
                        Sst_Model sst_model = new Sst_Model(id,assembly_id,name_eng,name_hi,shift,assembly_name_eng,assembly_name_hi);
                        sstModelArrayList.add(sst_model);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Sst_RC_Adapter(Sst.this, sstModelArrayList);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Sst.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });


    }
}