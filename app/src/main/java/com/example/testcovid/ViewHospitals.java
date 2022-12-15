package com.example.testcovid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

public class ViewHospitals extends AppCompatActivity{
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;
    private LocationManager locationManager;
    private double latitude=55.792139,longitude=49.122135;
    ArrayAdapter<Hospital> arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_view_hospitals);


        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);


        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        //точка обзора
        IMapController mapController = map.getController();
        mapController.setZoom(15);
        loadLocation(); //для загрузки геолокации
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);

        String[] permissions = new String[]{"Manifest.permission.ACCESS_FINE_LOCATION","Manifest.permission.WRITE_EXTERNAL_STORAGE"};
        requestPermissionsIfNecessary(permissions);

        showHospitals();
    }

    @SuppressLint("MissingPermission")
    private void loadLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, location -> {
        });

        latitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
        longitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
        System.out.println(latitude+"  "+longitude);
    }

    public void goB(View view){
        Intent intent = new Intent(this, ListBeforeMap.class);
        startActivity(intent);
    }

    private void showHospitals(){
        DataBaseAdapter adapter = new DataBaseAdapter(this);
        adapter.open();

        //
        GeoPoint myGeoPoint = new GeoPoint(latitude,longitude);
        OverlayItem overlayItem = new OverlayItem("Мое местоположение", "Я", myGeoPoint);
        Drawable markerDrawable= this.getResources().getDrawable(R.drawable.loc2);
        Drawable markerDrawable1= this.getResources().getDrawable(R.drawable.h);
        overlayItem.setMarker(markerDrawable);

        ArrayList<OverlayItem> overlayItemArrayList = new ArrayList<OverlayItem>();
        overlayItemArrayList.add(overlayItem);
        MapView mapView = (MapView) findViewById(R.id.map);
        //

        List<Hospital> stores = adapter.getStores();

        for(Hospital hosp: stores){
            double lat = hosp.getLatitude();
            double longi = hosp.getLongitude();

            GeoPoint geoPoint = new GeoPoint(lat,longi);
            OverlayItem item = new OverlayItem("1", "1", geoPoint);
            item.setMarker(markerDrawable1);
            overlayItemArrayList.add(item);
        }

        ItemizedOverlay<OverlayItem> locationOverlay = new ItemizedIconOverlay<OverlayItem>(this, overlayItemArrayList, null);
        mapView.getOverlays().add(locationOverlay);
        adapter.close();
    }



    ///
    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }


    }


}
