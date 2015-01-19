package epsz.gpsalarm.mapactivty;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import epsz.alarmapp.Interactors.GeoCircle;
import epsz.alarmapp.Interactors.Interactors;
import epsz.gpsalarm.GoogleMapAdapter;
import epsz.gpsalarm.MapApplication;
import epsz.gpsalarm.R;

import static com.google.android.gms.common.api.GoogleApiClient.*;

public class MapActivity extends ActionBarActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private static final String TAG = "MapActivity";
    GoogleMap map;
    private MapActivityController controller;
    private GoogleApiClient googleApiClient;

    private Interactors getInteractors() {
        return MapApplication.getInstance().getInteractors();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        setupControllers();
        setupPresenters(map);

        this.map = map;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                controller.addAlarmAtLocation(latLng, 100000);
            }
        });
    }

    private void setupControllers() {
        this.controller = new MapActivityController();
        this.controller.addAlarmInteractor = getInteractors().getAddAlarmInteractor();
        this.controller.updateStateInteractor = getInteractors().getUpdateInteractor();
    }

    private void setupPresenters(GoogleMap map) {
        MapActivityPresenter presenter = new MapActivityPresenter(new GoogleMapAdapter(map));
        getInteractors().getAddAlarmInteractor().setPresenter(presenter);
        getInteractors().getUpdateInteractor().setPresenter(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        googleApiClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000); // Update location every second

        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "GoogleApiClient connection has been suspend");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "GoogleApiClient connection has failed");
    }

    @Override
    public void onLocationChanged(Location loc) {
        controller.updateToLocation(loc.getLatitude(), loc.getLongitude(), loc.getAccuracy());
    }
}
