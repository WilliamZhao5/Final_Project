package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    private String eventName;
    public String locationName;
    private int time;
    private int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText event = findViewById(R.id.eventName);
        if (event != null) {
            eventName = event.getText().toString();
        }

        EditText location = findViewById(R.id.eventLocation);
        if (location != null) {
            locationName = location.getText().toString();

        }

        EditText eventTime = findViewById(R.id.eventTime);
        if (eventTime != null) {
            if (eventTime.getText().toString().length() != 0) {
                time = Integer.parseInt(eventTime.getText().toString());
            }
        }

        EditText eventDate = findViewById(R.id.eventDate);
        if (eventDate != null) {
            if (eventDate.getText().toString().length() != 0) {
                date = Integer.parseInt(eventDate.getText().toString());
            }
        }

        final Button addEvent = findViewById(R.id.addEvent);

        addEvent.setOnClickListener(unused -> addClicked());

    }

    private void addClicked() {
        Intent intent = new Intent(this, showsMap.class);
        startActivity(intent);
        finish();
    }
}
