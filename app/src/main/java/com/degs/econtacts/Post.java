package com.degs.econtacts;

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

public class Post extends AppCompatActivity {
    ArrayList<Post_Model> postModelArrayList;
    RecyclerView recyclerView;
    SearchView searchView_post;
    ArrayAdapter<Post_Model> postModelArrayAdapter;
    Post_RC_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView_post = findViewById(R.id.searchview_toolbar);
        recyclerView = findViewById(R.id.recyclerview_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postModelArrayList = new ArrayList<>();
        //Search functionality start here
        searchView_post.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        fillDepartments();
    }
    public void filterList(String text) {
        ArrayList<Post_Model> filteredList = new ArrayList<>();
        for (Post_Model postModel : postModelArrayList) {
            if (postModel.name_eng.toLowerCase().contains(text.toLowerCase())
                    || postModel.name_eng.toLowerCase().contains(text.toLowerCase())

                    || postModel.name_hi.toLowerCase().contains(text.toLowerCase())
                    || postModel.short_name_eng.toLowerCase().contains(text.toLowerCase())
                    || postModel.short_name_hi.toLowerCase().contains(text.toLowerCase())
                    || postModel.short_name_eng.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(postModel);
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
        AndroidNetworking.get("https://ndpm.vinayakinfotech.co.in/api/allPosts").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject post = response.getJSONObject(i);
                        int id = post.getInt("id");
                        int rank = post.getInt("rank");
                        String name_eng = post.getString("name_eng");
                        String name_hi = post.getString("name_hi");
                        String short_name_eng = post.getString("short_name_eng");
                        String dept_short_name_eng = post.getJSONObject("department").getString("short_name_eng");
                        String short_name_hi = post.getString("short_name_hi");
                        String dept_short_name_hi = post.getJSONObject("department").getString("short_name_hi");
                        Post_Model postModel = new Post_Model(id, rank, name_eng, name_hi, short_name_eng, short_name_hi,dept_short_name_eng,dept_short_name_hi);
                        postModelArrayList.add(postModel);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapter = new Post_RC_Adapter(Post.this, postModelArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Post.this, "Error" + anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", "Error" + anError.getLocalizedMessage());

            }
        });


    }
}