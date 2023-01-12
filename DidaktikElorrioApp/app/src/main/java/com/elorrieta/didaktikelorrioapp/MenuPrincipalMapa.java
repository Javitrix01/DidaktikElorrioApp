package com.elorrieta.didaktikelorrioapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.text.LineBreaker;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;
import com.elorrieta.didaktikelorrioapp.entities.Activity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
<<<<<<< HEAD
=======
import com.google.android.material.snackbar.Snackbar;
>>>>>>> main
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalMapa extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback{

    private Location loc;
    private LocationSource.OnLocationChangedListener mListener;
    ArrayList<String> menuEntries = new ArrayList<>();
    //localizaciones de las actividades
    LatLng Kurutziaga = new LatLng(43.1281365,-2.5397199);
    LatLng Basilika = new LatLng(43.1301944, -002.5425889);
    LatLng Udaletxea = new LatLng(43.1305250, -002.5420667);
    LatLng Gozotegia = new LatLng(43.1309750, -002.5432472);
    LatLng Jauregia = new LatLng(43.1306917, -002.5439056);
    LatLng Errebonbilloa = new LatLng(43.1301361, -002.5420306);
    LatLng Nekropolia = new LatLng(	43.1399056, -002.5359750);
    LatLng Elorrieta = new LatLng(43.28378511002439, -2.9643885432325296);
    Location KurutziagaLocation = new Location("");
    Location BasilikaLocation = new Location("");
    Location UdaletxeaLocation = new Location("");
    Location GozotegiaLocation = new Location("");
    Location JauregiaLocation = new Location("");
    Location ErrebonbilloaLocation = new Location("");
    Location NekropoliaLocation = new Location("");
    Location ElorrietaLocation = new Location("");


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
    private GoogleMap infoMap;

    @SuppressLint({"MissingPermission", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableMyLocation();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3, 1, locationListener);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_mapa);

        //inicializar la API de google
        Places.initialize(getApplicationContext(),"AIzaSyAfAYPmTFEr2ykofZMnfXo9fwk-s5m-wzc");


        //poner la latitud y longitud de cada localizacion
        KurutziagaLocation.setLatitude(43.1281365);
        KurutziagaLocation.setLongitude(-2.5397199);
        BasilikaLocation.setLatitude(43.1301944);
        BasilikaLocation.setLongitude(-002.5425889);
        UdaletxeaLocation.setLatitude(43.1305250);
        UdaletxeaLocation.setLongitude(-002.5420667);
        GozotegiaLocation.setLatitude(43.1309750);
        GozotegiaLocation.setLongitude(-002.5432472);
        JauregiaLocation.setLatitude(43.1306917);
        JauregiaLocation.setLongitude(-002.5439056);
        ErrebonbilloaLocation.setLatitude(43.1301361);
        ErrebonbilloaLocation.setLongitude(-002.5420306);
        NekropoliaLocation.setLatitude(43.1399056);
        NekropoliaLocation.setLongitude(-002.5359750);

        ElorrietaLocation.setLatitude(43.28378511002439);
        ElorrietaLocation.setLongitude(-2.9643885432325296);



        //MAPA PRINCIPAL
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapPRUEBA);
        mapFragment.getMapAsync(this);

        //oculta la barra de arriba
        getSupportActionBar().hide();

        //asignaciones de elementos del layout a variables
        Button botonAbrirMenu = findViewById(R.id.btnMenuActividades);
        Button botonAbrirMapa = findViewById(R.id.btnMapa);
        ListView lista = findViewById(R.id.listaMenuPrincipal);

        //propiedades asignadas al crearse la actividad
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            lista.setX(-200);
        }
        lista.setClickable(true);
        lista.setVisibility(View.INVISIBLE);

        //funcion que carga las actividades en la lista
        cargarActividadesLista(lista, botonAbrirMenu, botonAbrirMapa);

        //accion del boton para ver la lista
        botonAbrirMenu.setOnClickListener(view -> {
            //aplica la animacion para cuando aparezca la lista
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                lista.animate().translationX(0);
            }
            lista.setVisibility(View.VISIBLE);
        });
        //accion del boton para volver al mapa
        botonAbrirMapa.setOnClickListener(view -> {
            //aplica la animacion para cuando desaparezca la lista
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                lista.animate().translationX(-200);
            }
            lista.setVisibility(View.INVISIBLE);
        });
    }

    /**
     * Funcion para cargar las acitivades en la lista y que se puedan visualizar.
     *
     * @param lista          Lista en la que se van a cargar las actividades.
     * @param botonAbrirMenu Boton que abre el menu de la lista de actividades.
     * @param botonAbrirMapa Boton que cierra el menu de actividades y vuelve al mapa.
     */
    public void cargarActividadesLista(ListView lista, Button botonAbrirMenu, Button botonAbrirMapa) {
        //crea un arraylist para guardar el texto en la lista
        ArrayList<String> listaActividades = new ArrayList<>();

        //asigna la instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        //recoge todas las actividades de la base de datos
        List<Activity> actividades = db.activityDao().getAll();

        //guarda el texto de todas las actividades para mostrarlas en la lista
        for (int i = 0; i < 7; i++) {
            String stringVariable = (i + 1) + ". aktibitatea: \"" + actividades.get(i).getActivity_name() + "\"";
            listaActividades.add(stringVariable);
        }

        //crea el adaptador para la lista
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.list_card, listaActividades);
        lista.setAdapter(arrayAdapter);

        //crea un escuchador para que se pueda mostrar la informacion de las actividades cuando se hace click en ellas
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //aplica la animacion para cuando desaparezca la lista
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    lista.animate().translationX(-200);
                }
                lista.setVisibility(View.INVISIBLE);
                //desactiva los botones y opciones del mapa
                botonAbrirMapa.setClickable(false);
                botonAbrirMenu.setClickable(false);
                map.getUiSettings().setScrollGesturesEnabled(false);

                map.getUiSettings().setZoomGesturesEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                map.getUiSettings().setRotateGesturesEnabled(false);


                //abre la nueva actividad
                FrameLayout rootLayout = findViewById(android.R.id.content);
                View.inflate(getApplicationContext(), R.layout.activity_info_del_menu, rootLayout);

                //asigna los TextView
                TextView tvTitulo = findViewById(R.id.textViewTituloAct);
                TextView tvDescripcion = findViewById(R.id.textViewDescripcion);

                //añade la informacion
                tvTitulo.setText(actividades.get(i).getActivity_name());
                tvDescripcion.setText(actividades.get(i).getActivity_description());

                //justifica el texto de la descripcion (solo disponible a partir de Android 8.0+)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    tvDescripcion.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                }

                //latitud y longitud del mapa
                ImageView mapaInfoActividad = findViewById(R.id.infoMap);
                LinearLayout layoutInfoActividad = findViewById(R.id.linearLayout6);
                String lat = actividades.get(i).getActivity_coords_lat();
                String lng = actividades.get(i).getActivity_coords_long();

                String url = "https://maps.google.com/maps/api/staticmap?center=" + lat + "," + lng + "&zoom=15&size=200x200&sensor=false&key=AIzaSyAfAYPmTFEr2ykofZMnfXo9fwk-s5m-wzc";
                //MAPA INFO
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    new DownloadImageTask(findViewById(R.id.infoMap))
                            .execute(url);
                }

                String url = "https://maps.google.com/maps/api/staticmap?center=" + lat + "," + lng + "&zoom=19&size=600x355&markers=color:red%7Clabel:%7C" + lat + "," + lng + "&sensor=false&key=AIzaSyAfAYPmTFEr2ykofZMnfXo9fwk-s5m-wzc";
                //MAPA INFO
                new DownloadImageTask(mapaInfoActividad)
                        .execute(url);


                Button botonVolver = findViewById(R.id.btnitzuli);
                botonVolver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //activa los botones
                        botonAbrirMapa.setClickable(true);
                        botonAbrirMenu.setClickable(true);
                        map.getUiSettings().setScrollGesturesEnabled(true);

                        map.getUiSettings().setZoomGesturesEnabled(true);
                        map.getUiSettings().setMyLocationButtonEnabled(true);
                        map.getUiSettings().setRotateGesturesEnabled(true);


                        //esconde la actividad
                        FrameLayout rootLayout = findViewById(android.R.id.content);
                        rootLayout.removeViewAt(rootLayout.getChildCount() - 1);
                    }
                });
            }
        });
    }

    @SuppressLint("NewApi")
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

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
                .title("1 - Kurutziagako Gurutzea")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Basilika)
                .title("2 - Sortzez Garbiaren Basilika")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Udaletxea)
                .title("3 - Elorrioko Udaletxea")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Gozotegia)
                .title("4 - Pello Gozotegia")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Jauregia)
                .title("5 - Arespakotxaga Jauregia")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Errebonbilloa)
                .title("6 - Errebonbilloak")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Nekropolia)
                .title("7 - Arginetako Nekropolia")
                .alpha(0.7f));
        map.addMarker(new MarkerOptions()
                .position(Elorrieta)
                .title("Elorrieta"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Kurutziaga));
        map.setMapType(googleMap.MAP_TYPE_HYBRID);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);

        //ajusta el zoom del mapa
        FusedLocationProviderClient fusedLocationProviderClient;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            float zoom = 18.0f; //el valor maximo es 21!!
                            LatLng lastKnownLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng, zoom));
                        }
                    }
                });
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
         PermissionUtils.requestLocationPermissions(this, LOCATION_PERMISSION_REQUEST_CODE, true);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        //Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        //Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
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
                android.Manifest.permission.ACCESS_FINE_LOCATION) || PermissionUtils
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

    //TODO:_______
    //PARTE QUE CONTROLA EL ZOOM DEL MAPA HACIA EL SITIO EN EL QUE ESTA EL TELEFONO

    public void activate(LocationSource.OnLocationChangedListener listener)
    {
        mListener = listener;
    }

    public void deactivate()
    {
        mListener = null;
    }

    public void onLocationChanged(Location location)
    {
        if( mListener != null )
        {
            mListener.onLocationChanged( location );

            LatLngBounds bounds = this.map.getProjection().getVisibleRegion().latLngBounds;

            if(!bounds.contains(new LatLng(location.getLatitude(), location.getLongitude())))
            {
                //Move the camera to the user's location if they are off-screen!
                map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
            }
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
                    //Intent intent = new Intent(MapsActivity.this,Activity2.class);
                    //MapsActivity.this.startActivity(intent);
                } else {


        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
        @Override
        public void onLocationChanged(Location location) {

            //crear una instancia de la base de datos
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            //coge todas las actividades
            List<Activity> actividades;
            actividades = db.activityDao().getAll();

            if(location != null) {
                /*
                 * Solo cambiar la variable loc que es un marcador(anteriormente creado)
                 * la comparativa es la distancia al marcador ej: 10 es en metros
                 * Descomentar el intent provocara que cuando se este a menos de X metros se abra una nueva actividad
                 */
                //TODO: Acordarse de cambiar el valor de isActivity_visited a true cuando se termine una actividad para poder pasar a la siguiente
                if ((location.distanceTo(KurutziagaLocation)<25)&&(!actividades.get(6).isActivity_visited())){
                    //inicia primera actividad
                    if ((location.distanceTo(BasilikaLocation)<25)&&(actividades.get(0).isActivity_visited())) {
                        //inicia segunda actividad
                        if ((location.distanceTo(UdaletxeaLocation)<25)&&(actividades.get(1).isActivity_visited())){
                            //inicia tercera actividad
                            if ((location.distanceTo(GozotegiaLocation)<25)&&(actividades.get(2).isActivity_visited())){
                                //inicia cuarta actividad
                                if ((location.distanceTo(JauregiaLocation)<25)&&(actividades.get(3).isActivity_visited())){
                                    //inicia quinta actividad
                                    if ((location.distanceTo(ErrebonbilloaLocation)<25)&&(actividades.get(4).isActivity_visited())){
                                        //inicia sexta actividad
                                        if ((location.distanceTo(NekropoliaLocation)<25)&&(actividades.get(5).isActivity_visited())){
                                            //inicia septima actividad
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    //nada
                }
            }
        }
    };
}