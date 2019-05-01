package com.example.appreciationofarticles;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TextActivity extends AppCompatActivity {
    private static final String TAG = "Train Search";
    private static RequestQueue requestQueue;
    private int count = 0;
    private int place = 0;
    private JSONArray articles;
    private boolean start = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView secondPage = findViewById(R.id.textView);
        secondPage.setBackgroundColor(Color.argb(150, 255, 255, 255));

        requestQueue = Volley.newRequestQueue(this);
        String websit = getIntent().getStringExtra("API");
        callAPI(websit);
        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articles == null) {
                    return;
                }
                place = 0;
                getPage(place);
            }
        });
        Button previous =  findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articles == null) {
                    return;
                }
                if (place > 0) {
                    place--;
                }
                getPage(place);
            }
        });
        Button next =  findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articles == null) {
                    return;
                }
                if (place < articles.length() - 1) {
                    place++;
                }
                getPage(place);
            }
        });
        Button link =  findViewById(R.id.link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articles == null) {
                    return;
                }
                connect();
            }
        });
        Button home =  findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    protected void onPause() {super.onPause();}
    public void callAPI(final String address) {
        try {
            JsonObjectRequest ObjectRequest = new JsonObjectRequest(Request.Method.GET, address, null,
                    new Response.Listener<JSONObject>() {
                        public void onResponse(final JSONObject response) {
                            getResource(response);
                        }
                    }, new Response.ErrorListener() {
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            ObjectRequest.setShouldCache(false);
            requestQueue.add(ObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getResource(final JSONObject json) {
        try {
            articles = json.getJSONArray("articles");
            TextView titles = findViewById(R.id.textView);
            JSONObject article = articles.getJSONObject(0);
            String title = article.getString("title");
            String description = article.getString("description");
            titles.setText("  " + 1 + "\n\n" + "Title: " + "\n" + title + "\n\n" + "\n\n" + "Description: " + "\n" + description);
        } catch (JSONException e) {
        }
    }
    public void getPage(final int place) {
        try {
            TextView titles = findViewById(R.id.textView);
                    for (int i = 0; i < articles.length(); i++) {
                        if (i == place) {
                            JSONObject article = articles.getJSONObject(i);
                            String title = article.getString("title");
                            String description = article.getString("description");
                            titles.setText("  " + (i + 1) + "\n\n" + "Title: " + "\n" + title + "\n\n" + "\n\n" + "Description: " + "\n" + description);
                            break;
                }
            }
        } catch (JSONException e) {
        }
    }
    public void connect() {
        try {
            String toConnect = articles.getJSONObject(place).getString("url");
            Uri website = Uri.parse(toConnect);
            Intent intent = new Intent();
            intent.setAction(("android.intent.action.VIEW"));
            intent.setData(website);
            startActivity(intent);
        } catch (JSONException e) {
        }
    }
}
