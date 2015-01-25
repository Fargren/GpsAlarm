package epsz.gpsalarm.mapactivty;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.GeoCircle;
import epsz.alarmapp.Presenter;
import epsz.gpsalarm.Map;
import epsz.gpsalarm.Ringer;

public class MapActivityPresenter implements Presenter {

    private Map map;
    private Ringer ringer;

    public MapActivityPresenter(Map map, Ringer ringer) {
        this.map = map;
        this.ringer = ringer;
    }

    @Override
    public void addAlarmAtLocation(GeoCircle location) {
        CircleOptions options = new CircleOptions().center(new LatLng(location.latitude, location.longitude))
                .radius(location.radius);
        map.addAlarmCircle(options);
    }

    @Override
    public void ringAlarm() {
        ringer.ring();
    }

    @Override
    public void addAlarm(Alarm alarm) {

    }

    @Override
    public void showAlarms(List<Alarm> alarms) {

    }

    @Override
    public void stopAlarm() {
        ringer.stop();
    }
}
