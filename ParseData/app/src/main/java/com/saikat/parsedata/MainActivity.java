package com.saikat.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    String url = "https://jsonplaceholder.typicode.com/todos";
    String getApiUrl = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //queue = Volley.newRequestQueue(this);
        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        TextView textView = findViewById(R.id.tv_textview);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getApiUrl, null,
           response -> {
               try {
                   textView.setText(response.getString("title"));
                   Log.d("jsonObj", "onCreate: " +response.getString("title"));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }, error -> {
            Log.d("", "onCreate: Failed!");
        });
        queue.add(jsonObjectRequest);


        //getJsonArrayRequest();
        //getString(queue);
    }

    private void getJsonArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d("JSON", "onCreate: " + jsonObject.getString("title"));
//                                Log.d("JSON", "onCreate: "+jsonObject.getInt("userId"));
//                                Log.d("JSON", "onCreate: "+jsonObject.getBoolean("completed"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("JSON", "onCreate: " + response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "onCreate Failed!");

                    }
                });
        queue.add(jsonArrayRequest);
    }

    private void getString(RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Main", "onCreate: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Main", "Failed to get info");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}


//If you ask me, how I feel when I stay at my office. Then I would say that out of a lot of happy people, I am the only unhappy person.

