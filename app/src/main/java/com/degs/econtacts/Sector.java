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

public class Sector extends AppCompatActivity {
ArrayList<Sector_Model> sectorModelArrayList;
Sector_RC_Adapter adapter;
RecyclerView recyclerView;
ProgressDialog progressDialog;
SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sector);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.recyclerview_sector);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sectorModelArrayList=new ArrayList<>();
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
        fillSectors();


    }


    public void filterList(String text) {
        ArrayList<Sector_Model> filteredList = new ArrayList<>();
        for (Sector_Model sectorModel : sectorModelArrayList) {
            if (sectorModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || sectorModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || sectorModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || String.valueOf(sectorModel.sector_no).toLowerCase().contains(text.toLowerCase())
                    || sectorModel.assembly_name_eng.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sectorModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillSectors() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allSectors").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject sector = response.getJSONObject(i);
                        int id = sector.getInt("id");
                        String name_eng = sector.getString("name_eng");
                        String name_hi = sector.getString("name_hi");
                        int sector_no = sector.getInt("sector_no");
                        int assembly_id = sector.getInt("assembly_id");
                        int rank = sector.getInt("rank");
                        String assembly_name_eng = sector.getJSONObject("assembly").getString("name_eng");
                        JSONArray officers = sector.getJSONArray("officers");
                        ArrayList<Officer_Model> officersList = new ArrayList<>();
                        if (officers.length() > 1) {
                            for (int j = 0; j < officers.length(); j++) {
                                JSONObject officer = officers.getJSONObject(j);
                                int officer_id = officer.getInt("id");
                                int officer_rank = officer.getInt("rank");
                                String officer_name_eng = officer.getString("name_eng");
                                String officer_name_hi = officer.getString("name_hi");
                                String officer_mobile = officer.getString("mobile");
                                String officer_email = officer.getString("email");
                                officersList.add(new Officer_Model(officer_id, officer_rank, officer_name_eng, officer_name_hi, officer_mobile, officer_email));

                            }
                        } else {
                            officersList.add(new Officer_Model(100, 1000, "officer_name_eng", "officer_name_hi", "officer_mobile", "officer_email"));
                            officersList.add(new Officer_Model(101, 1000, "police_name_eng", "police_name_hi", "police_mobile", "police_email"));

                        }

                        Sector_Model sectorModel = new Sector_Model(id,assembly_id,sector_no,name_eng,name_hi,assembly_name_eng
                        ,officersList);
                        sectorModelArrayList.add(sectorModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Sector_RC_Adapter(Sector.this, sectorModelArrayList);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Sector.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });


    }
}