package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Action extends AppCompatActivity {
    /*TextView txtUser;*/
    RecyclerView recyclerView;
    List<User> users;
    String url = "https://60c03b18b8d367001755490d.mockapi.io/users";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        recyclerView=findViewById(R.id.userList);
        users=new ArrayList<>();

        extractUses();



       /* txtUser=(TextView)findViewById(R.id.txtUser);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(Action.this,response.toString(), Toast.LENGTH_LONG).show();
                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject=(JSONObject)response.get(i);
                        String id=jsonObject.getString("id");
                        String name=jsonObject.getString("name");
                        String age=jsonObject.getString("age");
                        txtUser.append(id+" Ho ten: "+name+" Tuoi: "+age+"\n");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Action.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);*/
    }

    private void extractUses() {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        User user = new User();
                        user.setId(jsonObject.getString("id".toString()));
                        user.setName(jsonObject.getString("name".toString()));
                        user.setAge(jsonObject.getString("age".toString()));

                        users.add(user);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter=new Adapter(getApplicationContext(),users);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","Error"+error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}