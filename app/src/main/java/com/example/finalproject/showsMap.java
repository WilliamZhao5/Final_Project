package com.example.finalproject;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class showsMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private EditText mSearchText;

    private String loca;
    private String eveName;
    private String eveTime;
    private String eveDate;

    final float defaultMapZoom = 17f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_map);

        mSearchText = findViewById(R.id.input_search);

        Intent intent = getIntent();
        loca = intent.getStringExtra("Location");
        eveName = intent.getStringExtra("Name");
        eveDate = intent.getStringExtra("Date");
        eveTime = intent.getStringExtra("Time");

        mSearchText.setText(loca);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init() {
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == keyEvent.ACTION_DOWN
                        || keyEvent.getAction() == keyEvent.KEYCODE_ENTER) {
                    //call searching
                    geoLocate();

                }
                return false;
            }
        });

    }

    private void geoLocate() {
        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(showsMap.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e("gg", "geolocate: IOException: " + e.getMessage());
        }

        if (list.size() > 0) {
            Address address = list.get(0);
            Log.d("PP", "found location: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), defaultMapZoom, eveName + " at " + loca + " at " + eveTime + " on " + eveDate);
        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        MarkerOptions options = new MarkerOptions().position(latLng).title(title);
        mMap.addMarker(options);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng location = new LatLng(40.104869, -88.227146);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.latitude, location.longitude), defaultMapZoom));
        mMap.addMarker(new MarkerOptions().position(location).title("Marker in UIUC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        init();
    }
}