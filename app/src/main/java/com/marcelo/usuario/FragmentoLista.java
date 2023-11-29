package com.marcelo.usuario;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.JarException;

public class FragmentoLista extends AppCompatActivity {

    private UserAdapter userAdapter;

    protected  void onCreated void getOnCreated(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista);


        //      String url = "http: //10.0.2.2:8098/api/getUsers";
        String url = "https://my-usuario-marcelo.onrender.com";




        RequestQueue queue = Volley.newRequestQueue(FragmentoLista.this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<User> users = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject userObject = response.getJSONObject(i);
                        Integer id = userObject.getInt("id");
                        String name = userObject.getString("name");
                        String email = userObject.getString("email");
                        users.add(new User(id, name, email));
                    } catch (JarException e) {
                        e.printStackTrace();
                    }
                }
                userAdapter = new UserAdapter(users, FragmentoLista.this);
                RecyclerView recyclerView = findViewById(R.id.reciclerView);
                recyclerView.setAdapter(UserAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(FragmentoLista.this);
                recyclerView.setLayoutManager(layoutManager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Agregar toast para aviso de error
            }
    });
        queue.add(request);
    }
}
