package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String eventName;
    public String locationName;
    private String time;
    private String date;

    //send email button
    EditText email, subject, message;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addEvent = findViewById(R.id.addEvent);

        addEvent.setOnClickListener(unused -> addClicked());
        //find button
        email = findViewById(R.id.emailAddress);
        subject = findViewById(R.id.emailSubject);
        message = findViewById(R.id.emailMessage);
        sendEmail = findViewById(R.id.sendEmail);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    private void sendEmail() {
        String getSubject;
        String getMessage;

        String mail = email.getText().toString().trim();

        String getMessage2 = message.getText().toString();
        String getSubject2 = subject.getText().toString().trim();

        String getMessage1 = eventName + " at " + locationName + " at " + time + " on " + date;
        String getSubject1 = "Event Reminder from Smart Notes";

        if (getSubject2.length() != 0 && getMessage2.length() != 0) {
            getSubject = getSubject2;
            getMessage = getMessage2;
        } else {
            getSubject = getSubject1;
            getMessage = getMessage1;
        }

        //send mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,getSubject,getMessage);

        javaMailAPI.execute();
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
                time = eventTime.getText().toString();
            }
        }

        EditText eventDate = findViewById(R.id.eventDate);
        if (eventDate != null) {
            if (eventDate.getText().toString().length() != 0) {
                date = eventDate.getText().toString();
            }
        }

        Intent intent = new Intent(this, showsMap.class);
        intent.putExtra("Location", locationName);
        intent.putExtra("Name", eventName);
        intent.putExtra("Time", time);
        intent.putExtra("Date", date);
        startActivity(intent);

        sendEmail();

        finish();
    }
}
