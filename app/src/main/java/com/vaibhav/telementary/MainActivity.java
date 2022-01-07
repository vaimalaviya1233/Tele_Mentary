package com.vaibhav.telementary;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        if (!pref.getBoolean("disclaimer_accepted", false)) {
            DialogFragment dialog = new DisclaimerDialogFragment();
            dialog.show(getSupportFragmentManager(), "DisclaimerDialogFragment");
        }
        BottomSheetDialogFragment bsdf = new BottomSheetDialogFragment();


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
    protected void onStop() {
        super.onStop();

    }

    private void clearCacher() {
        Toast.makeText(context,"clearCache Initiated",Toast.LENGTH_SHORT).show();
    }
}