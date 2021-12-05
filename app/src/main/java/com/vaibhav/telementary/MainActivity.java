package com.vaibhav.telementary;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ScrollView scrollView = findViewById(R.id.scroll_view);
        scrollView.setSmoothScrollingEnabled(false);
        recyclerView = findViewById(R.id.recycler_view);
        context = this;


        this.runOnUiThread(() -> {
            try {
                MyAdapter myAdapter = new MyAdapter(context);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(context, "ERROR While setting MyADAPTER", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", "Error in myAdaptor :( ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearCacher();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearCacher();
    }

    private void clearCacher() {
        Toast.makeText(context,"clearCache Initiated",Toast.LENGTH_SHORT).show();
    }
}