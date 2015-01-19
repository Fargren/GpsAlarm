package epsz.gpsalarm.mapactivty;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import epsz.alarmapp.Interactors.AlarmAdder;
import epsz.alarmapp.Interactors.GeoCircle;
import epsz.alarmapp.Interactors.LocationUpdater;
import epsz.alarmapp.Presenter;

public class MapActivityController {
    public AlarmAdder addAlarmInteractor;
    public LocationUpdater updateStateInteractor;

    public void addAlarmAtLocation(LatLng pos, double radius) {
        addAlarmAtLocation(pos.latitude, pos.longitude, radius);
    }

    public void addAlarmAtLocation(double latitude, double longitude, double radius) {
        GeoCircle area = new GeoCircle(latitude, longitude, radius);
        addAlarmInteractor.addAlarmAtLocation(area);
    }


    public void updateToLocation(double latitude, double longitude, double radius) {
        GeoCircle area = new GeoCircle(latitude, longitude, radius);
        updateStateInteractor.updateTo(area);
    }
}
