package com.example.appreciationofarticles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private String web;
    public void w(String a) {
        web = a;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button bitcoin = findViewById(R.id.bitcoin);
        bitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w("https://newsapi.org/v2/everything?q=bitcoin&from=2019-03-30&sortBy=publishedAt&apiKey=97c2aa8b232b4312bff8689c794ff106");
                startView();
            }
        });
        Button apple = findViewById(R.id.apple);
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w("https://newsapi.org/v2/everything?q=apple&from=2019-04-26&to=2019-04-26&sortBy=popularity&apiKey=97c2aa8b232b4312bff8689c794ff106");
                startView();
            }
        });
        Button techCrunch = findViewById(R.id.techCrunch);
        techCrunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=97c2aa8b232b4312bff8689c794ff106");
                startView();
            }
        });
        Button topBusinessHeadline = findViewById(R.id.topBusinessHeadline);
        topBusinessHeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=97c2aa8b232b4312bff8689c794ff106");
                startView();
            }
        });
        Button wallStreetJournal = findViewById(R.id.wallStreetJournal);
        wallStreetJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w("https://newsapi.org/v2/everything?domains=wsj.com&apiKey=97c2aa8b232b4312bff8689c794ff106");
                startView();
            }
        });
    }
    public void startView() {
        Intent intent = new Intent(this, TextActivity.class);
        intent.putExtra("API", web);
        startActivity(intent);
    }
}






