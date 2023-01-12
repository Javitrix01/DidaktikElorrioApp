package com.elorrieta.didaktikelorrioapp;

import android.Manifest.permission;
import android.annotation.SuppressLint;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * This demo shows how GMS Location can be used to check for changes to the users location.  The "My
 * Location" button uses GMS Location to set the blue dot representing the users location.
 * Permission for {@link android.Manifest.permission#ACCESS_FINE_LOCATION} and {@link
 * android.Manifest.permission#ACCESS_COARSE_LOCATION} are requested at run time. If either
 * permission is not granted, the Activity is finished with an error message.
 */
public class MapsActivity extends AppCompatActivity
        implements
        OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {
    ListView listView;
    Button botonPrueba;
    Button botonPrueba2;
    private Location loc;
    ArrayList<String> menuEntries = new ArrayList<>();
    LatLng Kurutziaga = new LatLng(43.1281365,-2.5397199);
    LatLng Basilika = new LatLng(43.1301944, -002.5425889);
    LatLng Udaletxea = new LatLng(43.1305250, -002.5420667);
    LatLng Gozotegia = new LatLng(43.1309750, -002.5432472);
    LatLng Jauregia = new LatLng(43.1306917, -002.5439056);
    LatLng Errebonbilloa = new LatLng(43.1301361, -002.5420306);
    LatLng nekropolia = new LatLng(	43.1399056, -002.5359750);
    LatLng Elorrieta = new LatLng(43.28378511002439, -2.9643885432325296);

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in {@link
     * #onRequestPermissionsResult(int, String[], int[])}.
     */






    private boolean permissionDenied = false;

    private GoogleMap map;

    @SuppressLint({"MissingPermission", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableMyLocation();
        Places.initialize(getApplicationContext(),"AIzaSyAfAYPmTFEr2ykofZMnfXo9fwk-s5m-wzc");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3, 1, locationListener);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listView = findViewById(R.id.MenuLateral);
        botonPrueba = findViewById(R.id.botonprueba);
        botonPrueba2 = findViewById(R.id.botonprueba2);



        /**============================FUNCIONES ONCLICK DE LOS BOTONES=================== */
        botonPrueba.setOnClickListener(view -> {

            listView.setVisibility(View.VISIBLE);

        });
        botonPrueba2.setOnClickListener(view -> {

            listView.setVisibility(View.GONE);


        });


        /**==============================CARGAR MENU LATERAL==================================*/
        menuEntries.add("0 Aktibitatea: Elorrioko Mapa");
        menuEntries.add("1 Aktibitatea: Kurutziagako gurutzea");
        menuEntries.add("2 Aktibitatea: basilika");
        menuEntries.add("3 Aktibitatea: Udaletxe");
        menuEntries.add("4 Aktibitatea: pello gozotegia");
        menuEntries.add("5 Aktibitatea: Jauregia");
        menuEntries.add("6 Aktibitatea: Errebonbilloa");
        menuEntries.add("7 Aktibitatea: Nekropolia");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,menuEntries);
        listView.setAdapter(arrayAdapter);

    }//TERMINA EL onCreate
    /**==============================FUNCIONES DEL MAPA==================================*/
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        loc = new Location("Test");
        loc.setLatitude(Elorrieta.latitude);
        loc.setLongitude(Elorrieta.longitude);

        map.addMarker(new MarkerOptions()
                .position(Kurutziaga)
                .title("Lehenengo gurutze"));
        map.addMarker(new MarkerOptions()
                .position(Basilika)
                .title("Bigarren gurutze"));
        map.addMarker(new MarkerOptions()
                .position(Udaletxea)
                .title("Hirugarren gurutze"));
        map.addMarker(new MarkerOptions()
                .position(Gozotegia)
                .title("Laugarren gurutze"));
        map.addMarker(new MarkerOptions()
                .position(Jauregia)
                .title("Bostgarren gurutze"));
        map.addMarker(new MarkerOptions()
                .position(Errebonbilloa)
                .title("Seigarren gurutze"));
        map.addMarker(new MarkerOptions()
                .position(nekropolia)
                .title("Lehenengo gurutze"));
        map.addMarker(new MarkerOptions()
                .position(Elorrieta)
                .title("Elorrieta"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Kurutziaga));
        map.setMapType(googleMap.MAP_TYPE_HYBRID);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);

    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {

        if (ContextCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {permission.ACCESS_FINE_LOCATION}, 0);
            ActivityCompat.requestPermissions(this, new String[] {permission.ACCESS_COARSE_LOCATION}, 0);
        }

       PermissionUtils.requestLocationPermissions(this, LOCATION_PERMISSION_REQUEST_CODE, true);

        // [START maps_check_location_permission]
        // 1. Check if permissions are granted, if so, enable the my location layer
      // if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        //    return;

         //   PermissionUtils.requestLocationPermissions(this, LOCATION_PERMISSION_REQUEST_CODE, true);


        // 2. Otherwise, request location permissions from the user.

        // [END maps_check_location_permission]
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    // [START maps_check_location_permission_result]
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION) || PermissionUtils
                .isPermissionGranted(permissions, grantResults,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Permission was denied. Display an error message
            // [START_EXCLUDE]
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true;
            // [END_EXCLUDE]
        }
    }
    // [END maps_check_location_permission_result]

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
        @Override
        public void onLocationChanged(Location location) {
            if(location != null) {
/**Solo cambiar la variable loc que es un marcador(anteriormente creado)
 * la comparativa es la distancia al marcador ej: 50 es en metros
 * Descomentar el intent provocara que cuando se este a menos de X metros se habra una nueva actividad*/
                if (location.distanceTo(loc)<50){
                    Toast.makeText(MapsActivity.this, "Dentro", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(MapsActivity.this,Activity2.class);
                    //MapsActivity.this.startActivity(intent);
                } else {

                }
            }
        }
    };

}