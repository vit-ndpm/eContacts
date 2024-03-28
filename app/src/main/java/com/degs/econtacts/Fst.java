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
import java.util.Objects;

public class Fst extends AppCompatActivity {
    ArrayList<Fst_Model> fstModelArrayList;
    Fst_RC_Adapter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SearchView searchView;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fst);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        url = Objects.requireNonNull(getIntent().getExtras()).getString("url");

        fstModelArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_fst);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        searchView = findViewById(R.id.searchview_toolbar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
        progressDialog.setTitle("FST Loader");
        progressDialog.setMessage("Loading FSTs ....");
        progressDialog.show();
        fillFsts();
    }
    public void filterList(String text) {
        ArrayList<Fst_Model> filteredList = new ArrayList<>();
        for (Fst_Model fst_model : fstModelArrayList) {
            if (fst_model.name_eng.toLowerCase().contains(text.toLowerCase()) || fst_model.name_eng.toLowerCase().contains(text.toLowerCase())

                    || fst_model.name_hi.toLowerCase().contains(text.toLowerCase()) || fst_model.assembly_name_eng.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(fst_model);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillFsts() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject fst = response.getJSONObject(i);
                        int id = fst.getInt("id");
                        int assembly_id = fst.getInt("assembly_id");
                        String name_eng = fst.getString("name_eng");
                        String name_hi = fst.getString("name_hi");
                        String shift = fst.getString("shift");
                        String assembly_name_eng = fst.getJSONObject("assembly").getString("name_eng");
                        String assembly_name_hi = fst.getJSONObject("assembly").getString("name_hi");
                        JSONArray officers = fst.getJSONArray("officers");

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
                            officersList.add(new Officer_Model(100, 1000, "No_Officer", "No Officer", "9999999999", "officer_email"));
                            officersList.add(new Officer_Model(101, 1000, "No_Police_Office", "No_Police_Office ", "9999999999", "police_email"));

                        }

                        Fst_Model fst_model = new Fst_Model(id, assembly_id, name_eng, name_hi, shift, assembly_name_eng, assembly_name_hi, officersList);
                        fstModelArrayList.add(fst_model);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Fst_RC_Adapter(Fst.this, fstModelArrayList);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Fst.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });



    }
}