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

public class Booth extends AppCompatActivity {
    ArrayList<Booth_Model> boothModelArrayList;
    Booth_RC_Adapter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        boothModelArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_booth);
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
        progressDialog.setTitle("Polling Booth Loader");
        progressDialog.setMessage("Lodaing Polling Booths...");
        progressDialog.show();
        fillBooths();
    }

    private void fillBooths() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allPollingBooths")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject booth = response.getJSONObject(i);
                                Booth_Model boothModel = new Booth_Model();
                                boothModel.id=booth.getInt("id");
                                boothModel.ac_code=booth.getInt("ac_code");
                                boothModel.pollingStationCode=booth.getInt("PollingStationCode");
                                boothModel.pollingStationAuxillaryCode=booth.getInt("PollingStationAuxillaryCode");
                                boothModel.boothName=booth.getString("BoothName");
                                boothModel.bLOName=booth.getString("BLOName");
                                boothModel.bLOMobileNumber=booth.getString("BLOMobileNumber");
                                boothModel.personName=booth.getString("PersonName");
                                boothModel.personMobileNumber=booth.getString("PersonMobileNumber");
                                boothModel.personSTDCode=booth.getString("PersonSTDCode");
                                boothModel.personLandLineNumber=booth.getString("PersonLandLineNumber");
                                boothModel.thanaNearestVillage=booth.getString("ThanaNearestVillage");
//                                boothModel.phanaNearestVillageDistance=booth.getInt("ThanaNearestVillageDistance");
                                boothModel.thanaSTDPhon=booth.getString("ThanaSTDPhon");
                                boothModel.thanaMobileNumber=booth.getString("ThanaMobileNumber");
//                                boothModel.sectorNumber=booth.getInt("SectorNumber");
                                boothModel.sectorMagistrateName=booth.getString("SectorMagistrateName");
                                boothModel.sectorOfficerDesignation=booth.getString("SectorOfficerDesignation");
                                boothModel.sectorOfficerName=booth.getString("SectorOfficerName");
                                boothModel.sectorOfficerCaderName=booth.getString("SectorOfficerCaderName");
                                boothModel.sectorMobileNumber=booth.getString("SectorMobileNumber");
                                boothModel.sectorPhoneNumber=booth.getString("SectorPhoneNumber");
                                boothModel.localContactPersonNameFirst=booth.getString("LocalContactPersonNameFirst");
                                boothModel.localContactPersonNameFirstMobile=booth.getString("LocalContactPersonNameFirstMobile");
                                boothModel.localContactPersonNameFirstPhone=booth.getString("LocalContactPersonNameFirstPhone");
                                boothModel.localContactPersonNameSecond=booth.getString("LocalContactPersonNameSecond");
                                boothModel.localContactPersonNameSecondMobile=booth.getString("LocalContactPersonNameSecondMobile");
                                boothModel.localContactPersonNameSecondPhone=booth.getString("LocalContactPersonNameSecondPhone");
                                boothModel.lat=booth.getDouble("lat");
                                boothModel.lng=booth.getDouble("lng");
                                boothModel.webcasting=booth.getInt("webcasting");
                                boothModel.cctv=booth.getInt("cctv");
                                boothModel.videography=booth.getInt("videography");
                                boothModel.critical=booth.getInt("critical");
                                boothModel.vulnarable=booth.getInt("vulnarable");
                                boothModel.status=booth.getInt("status");
                                boothModel.created_at=booth.getString("created_at");
                                boothModel.updated_at=booth.getString("updated_at");

                                boothModelArrayList.add(boothModel);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        Log.d("List", boothModelArrayList.iterator().toString());
                        adapter = new Booth_RC_Adapter(Booth.this, boothModelArrayList);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Booth.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error", "Error" + anError.getLocalizedMessage());
                        progressDialog.dismiss();
                    }
                });

    }
//    private void fillBooths() {
//        AndroidNetworking.initialize(this);
//        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allBooths")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject booth = response.getJSONObject(i);
//                                int id = booth.getInt("id");
//                                int assembly_id = booth.getInt("assembly_id");
//                                int booth_no = booth.getInt("booth_no");
//                                String booth_name_eng = booth.getString("name_eng");
//                                String booth_name_hi = booth.getString("name_hi");
//                                String address = booth.getString("address");
//                                String lat = booth.getString("lat");
//                                String lng = booth.getString("lng");
//                                int webcasting = booth.getInt("webcasting");
//                                int cctv = booth.getInt("cctv");
//                                int videography = booth.getInt("videography");
//                                int data_network = booth.getInt("data_network");
//                                int call_network = booth.getInt("call_network");
//                                int critical = booth.getInt("critical");
//                                int vulnarable = booth.getInt("vulnarable");
//
////
//                                String blo_name_eng = "";
//                                String blo_name_hi = "";
//                                String blo_mobile = "";
//                                JSONArray officers = booth.getJSONArray("officers");
//                                String sector_name_eng = booth.getJSONObject("sector").getString("name_eng");
//                                String sector_name_hi = booth.getJSONObject("sector").getString("name_hi");
//                                String assembly_name_eng = booth.getJSONObject("assembly").getString("code");
//                                String assembly_name_hi = booth.getJSONObject("assembly").getString("name_hi");
//                                if (officers.length() > 0) {
//                                    for (int j = 0; j < officers.length(); j++) {
//                                        JSONObject officer = officers.getJSONObject(j);
//                                        if (officer.getInt("role_id") == 14) {
//                                            blo_name_eng = officer.getString("name_eng");
//                                            blo_name_hi = officer.getString("name_hi");
//                                            blo_mobile = officer.getString("mobile");
//                                        }
//
//                                    }
//
//                                } else {
//                                    blo_name_eng = "";
//                                    blo_name_hi = "";
//                                    blo_mobile = "";
//                                }
//                                Booth_Model boothModel = new Booth_Model(id, assembly_id, booth_no, booth_name_eng, booth_name_hi, address,
//                                        blo_name_eng, blo_name_hi, blo_mobile, sector_name_eng, sector_name_hi, assembly_name_eng, assembly_name_hi,
//                                        lat, lng, webcasting, cctv, videography, critical, vulnarable, call_network, data_network);
//                                boothModelArrayList.add(boothModel);
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//
//                        }
//                        Log.d("List", boothModelArrayList.iterator().toString());
//                        adapter = new Booth_RC_Adapter(Booth.this, boothModelArrayList);
//                        recyclerView.setAdapter(adapter);
//                        progressDialog.dismiss();
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Toast.makeText(Booth.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e("error", "Error" + anError.getLocalizedMessage());
//                        progressDialog.dismiss();
//                    }
//                });
//
//    }

    public void filterList(String text) {
        ArrayList<Booth_Model> filteredList = new ArrayList<>();
        for (Booth_Model postModel : boothModelArrayList) {
            if (postModel.boothName.toLowerCase().contains(text.toLowerCase())
                    || postModel.bLOName.toLowerCase().contains(text.toLowerCase())
                    || Integer.toString(postModel.sectorNumber).toLowerCase().contains(text.toLowerCase())
                    || postModel.sectorMagistrateName.toLowerCase().contains(text.toLowerCase())
                    || postModel.sectorMobileNumber.toLowerCase().contains(text.toLowerCase())
                    || postModel.thanaNearestVillage.toLowerCase().contains(text.toLowerCase())
                    || postModel.thanaMobileNumber.toLowerCase().contains(text.toLowerCase())
                    || postModel.sectorOfficerDesignation.toLowerCase().contains(text.toLowerCase())
                    || String.valueOf(postModel.pollingStationCode).toLowerCase().contains(text.toLowerCase())
                    || String.valueOf(postModel.ac_code).toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(postModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }
}