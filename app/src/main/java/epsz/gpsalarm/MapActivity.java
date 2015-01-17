package epsz.gpsalarm;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapActivity extends ActionBarActivity implements OnMapReadyCallback {

    GoogleMap map;
    private MapActivityController controller;
    private MapActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        this.controller = new MapActivityController();
        this.controller.addAlarmInteractor = MapApplication.getInstance().getInteractors().getAddAlarmInteractor();

        this.presenter = new MapActivityPresenter(new GoogleMapAdapter(map));
        MapApplication.getInstance().getInteractors().getAddAlarmInteractor().setPresenter(presenter);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                controller.addAlarmAtLocation(latLng, 100000);
            }
        });
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
