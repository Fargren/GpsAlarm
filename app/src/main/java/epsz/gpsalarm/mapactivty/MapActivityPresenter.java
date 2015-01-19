package epsz.gpsalarm.mapactivty;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.Interactors.GeoCircle;
import epsz.alarmapp.Presenter;
import epsz.gpsalarm.Map;
import epsz.gpsalarm.MapApplication;

public class MapActivityPresenter implements Presenter {

    private Map map;

    public MapActivityPresenter(Map map) {
        this.map = map;
    }

    @Override
    public void addAlarmAtLocation(GeoCircle location) {
        CircleOptions options = new CircleOptions().center(new LatLng(location.latitude, location.longitude))
                .radius(location.radius);
        map.addAlarmCircle(options);
    }

    @Override
    public void ringAlarm() {
        Context context = MapApplication.getInstance().getBaseContext();
        Toast.makeText(context, "Alarm!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void addAlarm(Alarm alarm) {

    }

    @Override
    public void showAlarms(List<Alarm> alarms) {

    }

    @Override
    public void stopAlarm() {

    }
}
