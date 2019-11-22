package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private com.google.android.gms.maps.GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addEvent = findViewById(R.id.addEvent);

        addEvent.setOnClickListener(unused -> addClicked());

    }
    private void addClicked() {

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
