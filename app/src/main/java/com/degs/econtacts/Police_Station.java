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

public class Police_Station extends AppCompatActivity {
    ArrayList<Police_Station_Model>policeStationModelArrayList;
    RecyclerView recyclerView;
    Police_Station_RC_Adapter adapter;
    androidx.appcompat.widget.SearchView searchView;
    ProgressDialog progressDialog;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_police_station);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        policeStationModelArrayList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        recyclerView=findViewById(R.id.recyclerview_police);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView=findViewById(R.id.searchview_toolbar);
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

        url="https://ndpm.vinayakinfotech.co.in/api/allPoliceStations";
        progressDialog.setTitle("Police Station Loader");
        progressDialog.setMessage("Police Station Loading.....\n Please Wait");
        progressDialog.show();
        fillPoliceStations();

    }

    private void fillPoliceStations() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response",response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject policeStation = response.getJSONObject(i);
                                int id = policeStation.getInt("id");
                                int assembly_id = policeStation.getInt("assembly_id");
                                String name_eng = policeStation.getString("name_eng");
                                String name_hi = policeStation.getString("name_hi");
                                String address = policeStation.getString("address");
                                String landline = policeStation.getString("landline");
                                String email = policeStation.getString("email");
//                                String assembly_name = policeStation.getJSONObject("assembly").getString("name_eng");
                                String officer_name = policeStation.getJSONObject("officer").getString("name_eng");
                                String officer_mobile = policeStation.getJSONObject("officer").getString("mobile");
                                Police_Station_Model policeStationModel = new Police_Station_Model(id,assembly_id,name_eng,name_hi,address,landline,email,officer_name,officer_mobile);
                                policeStationModelArrayList.add(policeStationModel);
                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        adapter = new Police_Station_RC_Adapter(Police_Station.this, policeStationModelArrayList);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Police_Station.this, anError.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }
    public void filterList(String text) {
        ArrayList<Police_Station_Model> filteredList = new ArrayList<>();
        for (Police_Station_Model nodalmodel : policeStationModelArrayList) {
            if (nodalmodel.name_eng.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.address.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.name_hi.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.landline.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.address.toLowerCase().contains(text.toLowerCase()) ||
                    nodalmodel.officer_name.toLowerCase().contains(text.toLowerCase())) {
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