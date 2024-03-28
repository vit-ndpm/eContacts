package com.degs.econtacts;

import android.app.ProgressDialog;
import android.os.Bundle;
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

public class Polling_Party extends AppCompatActivity {
    ArrayList<Polling_Party_Model>polling_party_modelArrayList;
    Polling_Party_RC_Adapter adapter;
    RecyclerView recyclerView;
    SearchView searchView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_polling_party);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        polling_party_modelArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview_pp);
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
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Polling Party Loader");
        progressDialog.setMessage("Loading Polling Party...");
        progressDialog.show();
        fillPollingParty();

    }

    private void fillPollingParty() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allPollingParty")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray polling_parties) {
                        if (polling_parties.length()>0){
                            for (int i = 0; i <polling_parties.length() ; i++) {
                                try {
                                    JSONObject polling_party=polling_parties.getJSONObject(i);
                                    int id=polling_party.getInt("id");

                                    int booth_id=polling_party.getJSONObject("booth").getInt("id");
                                    int booth_no=polling_party.getJSONObject("booth").getInt("booth_no");
                                    String booth_name=polling_party.getJSONObject("booth").getString("name_eng");
                                    int assembly_id,assembly_code;String assembly_name;
                                    if (polling_party.getJSONObject("booth").getJSONObject("assembly")!=null){
                                         assembly_id=polling_party.getJSONObject("booth").getJSONObject("assembly").getInt("id");
                                         assembly_code=polling_party.getJSONObject("booth").getJSONObject("assembly").getInt("code");
                                         assembly_name=polling_party.getJSONObject("booth").getJSONObject("assembly").getString("name_eng");

                                    }else {
                                        assembly_id=0;
                                        assembly_name="";
                                        assembly_code=0;
                                    }





                                    String po_name=polling_party.getString("p0_name");
                                    String p1_name=polling_party.getString("p1_name");
                                    String p2_name=polling_party.getString("p2_name");
                                    String p3_name=polling_party.getString("p3_name");

                                    String po_mobile=polling_party.getString("p0_mobile");
                                    String p1_mobile=polling_party.getString("p1_mobile");
                                    String p2_mobile=polling_party.getString("p2_mobile");
                                    String p3_mobile=polling_party.getString("p3_mobile");

                                    Polling_Party_Model pollingPartyModel=new Polling_Party_Model(id,assembly_id,booth_id,booth_no,assembly_code,
                                            booth_name,assembly_name,po_name,p1_name,p2_name,p3_name,po_mobile,p1_mobile,p2_mobile,p3_mobile);
                                polling_party_modelArrayList.add(pollingPartyModel);


                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                        adapter=new Polling_Party_RC_Adapter(Polling_Party.this,polling_party_modelArrayList);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Polling_Party.this, anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });
    }
    public void filterList(String text) {
        ArrayList<Polling_Party_Model> filteredList = new ArrayList<>();
        for (Polling_Party_Model pollingPartyModel : polling_party_modelArrayList) {
            if (String.valueOf(pollingPartyModel.assembly_id).toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.booth_name.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.po_name.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.po_mobile.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.p1_mobile.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.p1_name.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.p2_mobile.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.p2_name.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.p3_mobile.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.p3_name.toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.booth_name.toLowerCase().contains(text.toLowerCase()) ||
                    String.valueOf(pollingPartyModel.booth_id).toLowerCase().contains(text.toLowerCase()) ||
                    String.valueOf(pollingPartyModel.booth_name).toLowerCase().contains(text.toLowerCase()) ||
                    pollingPartyModel.assembly_name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(pollingPartyModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {
                adapter.setFilteredList(filteredList);
            }
        }
    }
}