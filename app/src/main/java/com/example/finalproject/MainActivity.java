package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String eventName;
    public String locationName;
    private int time;
    private int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addEvent = findViewById(R.id.addEvent);

        addEvent.setOnClickListener(unused -> addClicked());

    }

    private void addClicked() {
        EditText event = findViewById(R.id.eventName);
        if (event != null) {
            eventName = event.getText().toString();
        }

        EditText location = findViewById(R.id.eventLocation);
        if (location != null) {
            locationName = location.getText().toString();
            System.out.println("Location: " + locationName);
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

        Intent intent = new Intent(this, showsMap.class);
        intent.putExtra("Location", locationName);
        startActivity(intent);
        finish();
    }
}
