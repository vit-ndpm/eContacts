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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Imp_Link extends AppCompatActivity {
    ArrayList<Imp_Link_Model>impLinkModelArrayList;
    RecyclerView recyclerView;
    Imp_Link_RC_Adapter adapter;
    SearchView searchView;
    ProgressDialog progressDialog;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imp_link);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.recyclerview_links);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        impLinkModelArrayList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        searchView=findViewById(R.id.searchview_toolbar);

        url = Objects.requireNonNull(getIntent().getExtras()).getString("url");
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
        progressDialog.setTitle("Loader");
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        filImpLinks();
    }

    private void filImpLinks() {
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {

                    @Override
                    public void onResponse(JSONArray allImpLinks) {
                        if (allImpLinks.length()>1)
                        {
                            for (int i = 0; i < allImpLinks.length(); i++) {
                                try {
                                    JSONObject link=allImpLinks.getJSONObject(i);
                                    int id=link.getInt("id");
                                    String title=link.getString("title");
                                    String description=link.getString("description");
                                    String url=link.getString("url");
                                    Category category=new Category(link.getJSONObject("category").getInt("id"),
                                            link.getJSONObject("category").getString("name_eng"),
                                            link.getJSONObject("category").getString("name_hi"),
                                            link.getJSONObject("category").getString("image"));
                                    impLinkModelArrayList.add(new Imp_Link_Model(id,title,description,url,category));

                                } catch (JSONException e) {
                                    Toast.makeText(Imp_Link.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    throw new RuntimeException(e);
                                }

                            }

                        }

                        adapter=new Imp_Link_RC_Adapter(Imp_Link.this,impLinkModelArrayList);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Imp_Link.this, anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });
    }
}