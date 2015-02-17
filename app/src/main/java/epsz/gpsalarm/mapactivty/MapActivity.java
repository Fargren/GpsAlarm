package epsz.gpsalarm.mapactivty;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import epsz.alarmapp.Interactors.Interactors;
import epsz.gpsalarm.GoogleMapAdapter;
import epsz.gpsalarm.MapApplication;
import epsz.gpsalarm.PositionTracker;
import epsz.gpsalarm.R;

import static com.google.android.gms.common.api.GoogleApiClient.Builder;

public class MapActivity extends ActionBarActivity implements OnMapReadyCallback {

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

        setupControllers();
        setupTracker();

        initMap();
        initStopAlarmButton();
    }

    private void initStopAlarmButton() {
        Button stopAlarmButton = (Button) this.findViewById(R.id.btnStopAlarm);
        stopAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.stopAlarm();
            }
        });
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupTracker() {
        PositionTracker tracker = new PositionTracker(controller);

        googleApiClient = new Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(tracker)
                .addOnConnectionFailedListener(tracker)
                .build();

        tracker.setGoogleApiClient(googleApiClient);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        setupPresenters(map);

        this.map = map;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                controller.addAlarmAtLocation(latLng, 100000);
            }
        });

        controller.refreshShownAlarms();
    }

    private void setupControllers() {
        this.controller = new MapActivityController();
        this.controller.addAlarmInteractor = getInteractors().getAddAlarmInteractor();
        this.controller.updateStateInteractor = getInteractors().getUpdateInteractor();
        this.controller.stopAlarmInteractor = getInteractors().getStopAlarmInteractor();
        this.controller.showAlarmsInteractor = getInteractors().getShowAlarmInteractor();
    }

    private void setupPresenters(GoogleMap map) {
        MapActivityPresenter presenter = new MapActivityPresenter(new GoogleMapAdapter(map), new ToastRinger());
        getInteractors().getAddAlarmInteractor().setPresenter(presenter);
        getInteractors().getUpdateInteractor().setPresenter(presenter);
        getInteractors().getStopAlarmInteractor().setPresenter(presenter);
        getInteractors().getShowAlarmInteractor().setPresenter(presenter);
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


}
