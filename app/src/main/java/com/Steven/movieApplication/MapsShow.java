package com.Steven.movieApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.Steven.movieApplication.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Created by steven on 6/2/2018.
 * show the maps for three cinema locations and their business hours.
 * use mapbox
 *
 *
 *
 * 
 */
public class MapsShow extends AppCompatActivity {
private MapView mapView;


@Override
protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        Mapbox.getInstance(this, "pk.eyJ1Ijoic3RhbmxlZTAzMTQiLCJhIjoiY2poeDl4ajI4MDlsNDN2cGtpc3cyaHVidCJ9.q8qoun3EOxsgUlOCYkH0xA");
        setContentView(R.layout.activity_mapshow);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
                //set the marker and content for three cinemas.
                @Override
                public void onMapReady(MapboxMap mapboxMap) {
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(-37.8136, 144.9631))
                                .title("CBD Cinema")
                                .snippet(getString(R.string.draw_marker_options_snippet)));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(-37.8830, 145.0930))
                                .title("Chadstone Cinema")
                                .snippet(getString(R.string.draw_marker_options_snippet)));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(-37.876999, 145.044236))
                                .title("Caufield Cinema")
                                .snippet(getString(R.string.draw_marker_options_snippet)));

                }
        });
}

@Override
public void onStart() {
        super.onStart();
        mapView.onStart();
        }

@Override
public void onResume() {
        super.onResume();
        mapView.onResume();
        }

@Override
public void onPause() {
        super.onPause();
        mapView.onPause();
        }

@Override
public void onStop() {
        super.onStop();
        mapView.onStop();
        }

@Override
public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
        }

@Override
protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        }

@Override
protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
}

        }

