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

public class Nodal extends AppCompatActivity {
    RecyclerView nodalRecyclerview;
    ArrayList<Nodal_Model> nodalmodelArrayList;
    Nodal_RC_Adapter adapter;
    SearchView searchView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nodal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressDialog = new ProgressDialog(this);

        searchView = findViewById(R.id.searchview_toolbar);
        searchView.clearFocus();
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
        nodalRecyclerview = findViewById(R.id.recyclerview_nodal);
        nodalRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        nodalmodelArrayList = new ArrayList<>();
        progressDialog.setTitle("Nodal Loader");
        progressDialog.setMessage("Loading Nodal....");
        progressDialog.show();
        fillNodals();
    }

    private void fillNodals() {

        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allNodals").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray nodals) {
                Log.d("response", String.valueOf(nodals));

                for (int i = 0; i < nodals.length(); i++) {
                    try {
                        JSONObject nodal = nodals.getJSONObject(i);
                        int id = nodal.getInt("id");
                        String jobs = nodal.getString("jobs");
                        String name_eng = nodal.getString("name_eng");
                        String name_hi = nodal.getString("name_hi");
                        JSONArray officers = nodal.getJSONArray("officers");

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


                        }
                        officersList.add(new Officer_Model(10000, 10000, "dummy name", "dummy", "9876543210", "dumy@gmail.com"));
                        officersList.add(new Officer_Model(10000, 10000, "dummy name", "dummy", "9876543210", "dumy@gmail.com"));
                        officersList.add(new Officer_Model(10000, 10000, "dummy name", "dummy", "9876543210", "dumy@gmail.com"));
                        officersList.add(new Officer_Model(10000, 10000, "dummy name", "dummy", "9876543210", "dumy@gmail.com"));
                        Nodal_Model nodalModel = new Nodal_Model(id, name_eng, name_hi, jobs, officersList);
                        nodalmodelArrayList.add(nodalModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter=new Nodal_RC_Adapter(Nodal.this,nodalmodelArrayList);
                nodalRecyclerview.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Nodal.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });

    }

    public void filterList(String text) {
        ArrayList<Nodal_Model> filteredList = new ArrayList<>();
        for (Nodal_Model nodalmodel : nodalmodelArrayList) {
            if (nodalmodel.name_eng.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.name_hi.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.jobs.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(nodalmodel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {
                adapter.setFilteredList(filteredList);
            }
        }
    }
}