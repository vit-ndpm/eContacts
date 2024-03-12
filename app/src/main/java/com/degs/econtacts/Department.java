package com.degs.econtacts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class Department extends AppCompatActivity {
    ArrayList<Department_Model> departmentModelArrayList;
    RecyclerView recyclerView;
    SearchView searchView_dept;
    ArrayAdapter<Department_Model> department_modelArrayAdapter;
    Department_RC_Adapter adapter;
    private ProgressDialog progressDialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_department);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView_dept = findViewById(R.id.searchview_toolbar);
        recyclerView = findViewById(R.id.recyclerview_dept);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        departmentModelArrayList = new ArrayList<>();
        progressDialogue=new ProgressDialog(this);
        //Search functionality start here
        searchView_dept.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        fillDepartments();
    }


    public void filterList(String text) {
        ArrayList<Department_Model> filteredList = new ArrayList<>();
        for (Department_Model departmentModel : departmentModelArrayList) {
            if (departmentModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || departmentModel.name_eng.toLowerCase().contains(text.toLowerCase())

                    || departmentModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || departmentModel.short_name_eng.toLowerCase().contains(text.toLowerCase())
                    || departmentModel.short_name_hi.toLowerCase().contains(text.toLowerCase())
                    || departmentModel.short_name_eng.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(departmentModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillDepartments() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allDepartments").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject department = response.getJSONObject(i);
                        int id = department.getInt("id");
                        int rank = department.getInt("rank");
                        String name_eng = department.getString("name_eng");
                        String name_hi = department.getString("name_hi");
                        String short_name_eng = department.getString("short_name_eng");
                        String short_name_hi = department.getString("short_name_hi");
                        Department_Model departmentModel = new Department_Model(id, rank, name_eng, name_hi, short_name_eng, short_name_hi);
                        departmentModelArrayList.add(departmentModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Department_RC_Adapter(departmentModelArrayList, Department.this);
                recyclerView.setAdapter(adapter);
                progressDialogue.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Department.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialogue.dismiss();

            }
        });


    }
}