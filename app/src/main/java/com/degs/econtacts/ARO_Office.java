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
import androidx.recyclerview.widget.GridLayoutManager;
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

public class ARO_Office extends AppCompatActivity {
    ArrayList<ARO_Office_Model>aroOfficeModelArrayList;
    ARO_Office_RC_Adapter adapter;
    SearchView searchView;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aro_office);
        progressDialog=new ProgressDialog(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        aroOfficeModelArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview_aro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        url = Objects.requireNonNull(getIntent().getExtras()).getString("url");
        Log.d("url", url);
        progressDialog.setTitle("Officer Loader");
        progressDialog.setTitle("Loading Officers.....");
        progressDialog.show();
        fillAROs();

    }


    private void fillAROs() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray officers) {
                Log.d("response", String.valueOf(officers));


                        for (int i = 0; i < officers.length(); i++) {
                            try {
                                JSONObject officer = officers.getJSONObject(i);
                                int id = officer.getInt("id");
                                String department = officer.getJSONObject("department").getString("name_eng");
                                String post = officer.getJSONObject("post").getString("name_eng");
                                String role = officer.getJSONObject("role").getString("name_eng");
                                String assembly = officer.getJSONObject("assembly").getString("name_eng");
                                String name_eng = officer.getString("name_eng");
                                String name_hi = officer.getString("name_hi");
                                String mobile = officer.getString("mobile");
                                String email = officer.getString("email");

                                ARO_Office_Model aroOfficeModel = new ARO_Office_Model(id,name_eng,name_hi,mobile,email,department,post,assembly,role);
                                aroOfficeModelArrayList.add(aroOfficeModel);

                            } catch (JSONException e) {
                                Toast.makeText(ARO_Office.this, "Error :"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                throw new RuntimeException(e);
                            }
                        }



                adapter = new ARO_Office_RC_Adapter(ARO_Office.this, aroOfficeModelArrayList);
                    recyclerView.setAdapter(adapter);



                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(ARO_Office.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });


    }
    public void filterList(String text) {
        ArrayList<ARO_Office_Model> filteredList = new ArrayList<>();
        for (ARO_Office_Model officerModel : aroOfficeModelArrayList) {
            if (officerModel.name_eng.toLowerCase().contains(text.toLowerCase()) ||
                    officerModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || officerModel.mobile.toLowerCase().contains(text.toLowerCase())
                    || officerModel.email.toLowerCase().contains(text.toLowerCase())
                    || officerModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || officerModel.post_name.toLowerCase().contains(text.toLowerCase())
                    || officerModel.dept_name.toLowerCase().contains(text.toLowerCase())
                    || officerModel.role_name.toLowerCase().contains(text.toLowerCase())
                    || officerModel.assembly_name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(officerModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }
}