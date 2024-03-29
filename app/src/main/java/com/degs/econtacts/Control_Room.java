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

public class Control_Room extends AppCompatActivity {
    ArrayList<Control_Room_Model> controlRoomModelArrayList;
    Control_Room_RC_Adapter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_control_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        controlRoomModelArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_control_room);
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
        progressDialog.setTitle("Control Room Loader");
        progressDialog.setMessage("Loading Control Rooms ....");
        progressDialog.show();
        fillControlRooms();
    }

    public void filterList(String text) {
        ArrayList<Control_Room_Model> filteredList = new ArrayList<>();
        for (Control_Room_Model controlRoomModel : controlRoomModelArrayList) {
            if (controlRoomModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || controlRoomModel.name_eng.toLowerCase().contains(text.toLowerCase())

                    || controlRoomModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || controlRoomModel.shift.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(controlRoomModel);
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            } else {

                adapter.setFilteredList(filteredList);
            }
        }
    }

    private void fillControlRooms() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allControlRooms").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject controlRoom = response.getJSONObject(i);
                        int id = controlRoom.getInt("id");
                        String name_eng = controlRoom.getString("name_eng");
                        String name_hi = controlRoom.getString("name_hi");
                        String shift = controlRoom.getString("shift");
                        JSONArray officers = controlRoom.getJSONArray("officers");

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

                        Control_Room_Model controlRoomModel = new Control_Room_Model(id, name_eng, name_hi, shift, officersList);
                        controlRoomModelArrayList.add(controlRoomModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Control_Room_RC_Adapter(Control_Room.this, controlRoomModelArrayList);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Control_Room.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());
                progressDialog.dismiss();


            }
        });


    }
}