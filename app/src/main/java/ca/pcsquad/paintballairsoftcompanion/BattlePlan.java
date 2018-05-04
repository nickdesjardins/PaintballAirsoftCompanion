package ca.pcsquad.paintballairsoftcompanion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.List;

public class BattlePlan extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    Marker marker;
    public Integer MarkerColor = 1;
    private static final int REQUEST_LOCATION = 2;

    ImageLoader imgLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battleplan);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toast.makeText(BattlePlan.this,
                "Please wait, GPS is positioning...", Toast.LENGTH_LONG).show();

        LocationManager locationManager;

        // Get the LocationManager object from the System Service LOCATION_SERVICE
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Create a criteria object needed to retrieve the provider
        Criteria criteria = new Criteria();

        // Get the name of the best available provider
        assert locationManager != null;
        String provider = locationManager.getBestProvider(criteria, true);

        // We can use the provider immediately to get the last known location
        //Location location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }

        Toast.makeText(BattlePlan.this,
                "permission checked...", Toast.LENGTH_SHORT).show();

        // request that the provider send this activity GPS updates every 20 seconds
        locationManager.requestLocationUpdates(provider, 20000, 0, this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        Toast.makeText(BattlePlan.this,
                "Markers...", Toast.LENGTH_LONG).show();
        //Images Buttons

        ImageButton btnBack = findViewById(R.id.btnback);
        ImageButton redG = findViewById(R.id.btnGRed);
        ImageButton blueG = findViewById(R.id.btnGBlue);
        ImageButton greenG = findViewById(R.id.btnGGreen);
        ImageButton yellowG = findViewById(R.id.btnGYellow);
        ImageButton red = findViewById(R.id.btnRed);
        ImageButton blue = findViewById(R.id.btnBlue);
        ImageButton green = findViewById(R.id.btnGreen);
        ImageButton yellow = findViewById(R.id.btnYellow);

        imgLoader.displayImage("drawable://" + R.drawable.btnbattleplanwhite, btnBack);
        imgLoader.displayImage("drawable://" + R.drawable.googlered, redG);
        imgLoader.displayImage("drawable://" + R.drawable.googleblue, blueG);
        imgLoader.displayImage("drawable://" + R.drawable.googlegreen, greenG);
        imgLoader.displayImage("drawable://" + R.drawable.googleyellow, yellowG);
        imgLoader.displayImage("drawable://" + R.drawable.bp_mini_marker_red, red);
        imgLoader.displayImage("drawable://" + R.drawable.bp_mini_marker_blue, blue);
        imgLoader.displayImage("drawable://" + R.drawable.bp_mini_marker_green, green);
        imgLoader.displayImage("drawable://" + R.drawable.bp_mini_marker_yellow, yellow);

        btnBack.setOnClickListener(imgButtonHandler);

        redG.setOnClickListener(btnRedGHandler);
        blueG.setOnClickListener(btnBlueGHandler);
        greenG.setOnClickListener(btnGreenGHandler);
        yellowG.setOnClickListener(btnYellowGHandler);
        red.setOnClickListener(btnRedHandler);
        blue.setOnClickListener(btnBlueHandler);
        green.setOnClickListener(btnGreenHandler);
        yellow.setOnClickListener(btnYellowHandler);
    }

    View.OnClickListener imgButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener btnRedHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 1;
        }
    };
    View.OnClickListener btnBlueHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 2;
        }
    };
    View.OnClickListener btnGreenHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 3;
        }
    };
    View.OnClickListener btnYellowHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 4;
        }
    };
    View.OnClickListener btnRedGHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 5;
        }
    };
    View.OnClickListener btnBlueGHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 6;
        }
    };
    View.OnClickListener btnGreenGHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 7;
        }
    };
    View.OnClickListener btnYellowGHandler = new View.OnClickListener() {
        public void onClick(View v) {
            MarkerColor = 8;
        }
    };





    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if ((grantResults.length == 1)
                    && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                return;
            }
            Toast.makeText(BattlePlan.this,
                    "You denied permission...", Toast.LENGTH_LONG).show();
            // Permission was denied or request was cancelled
        }
    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Sets the map type to be "hybrid"
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng ll) {

                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addressList = geocoder.getFromLocation(ll.latitude, ll.longitude, 1);
                    String string = addressList.get(0).getCountryName();
                    mMap.addMarker(new MarkerOptions().position(ll).title(string));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 10.2f));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BattlePlan.this.setMarker(ll.latitude, ll.longitude);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.remove();
                return false;
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        if (mMap != null)
            if(marker == null)
                firstMarker(location);
    }

    private void setMarker(double lat, double lng) {
        //if(marker != null)
           // marker.remove();

        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.bp_mini_marker_yellow))

        switch(MarkerColor){
            case 1: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bp_mini_marker_red", 70, 70)))
                    .draggable(true));
                     break;
            case 2: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bp_mini_marker_blue", 70, 70)))
                    .draggable(true));
                     break;
            case 3: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bp_mini_marker_green", 70, 70)))
                    .draggable(true));
                     break;
            case 4: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bp_mini_marker_yellow", 70, 70)))
                    .draggable(true));
                    break;
            case 5: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .draggable(true));
                break;
            case 6: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .draggable(true));
                break;
            case 7: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .draggable(true));
                break;
            case 8: marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .draggable(true));
                break;
            default: break;
        }

    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }

    private void firstMarker(Location location){

        if(marker != null)
            marker.remove();

            //  convert the location object to a LatLng object that can be used by the map API
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

            // zoom to the current location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition,16));

            // add a marker to the map indicating our current position
        marker = mMap.addMarker(new MarkerOptions()
                .position(currentPosition)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("I am here.")
                .draggable(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));

        // animate the zoom process
        mMap.animateCamera(CameraUpdateFactory.zoomTo(19), 2000, null);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
