package epsz.gpsalarm;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;

public class GoogleMapAdapter implements Map {
    private GoogleMap map;

    public GoogleMapAdapter(GoogleMap map) {
        this.map = map;
    }

    @Override
    public void addAlarmCircle(CircleOptions options) {
        map.addCircle(options);
    }
}
