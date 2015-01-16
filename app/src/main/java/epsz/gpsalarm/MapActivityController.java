package epsz.gpsalarm;

import epsz.alarmapp.Interactors.AlarmAdder;
import epsz.alarmapp.Interactors.GeoCircle;

public class MapActivityController {
    public AlarmAdder addAlarmInteractor;

    public void addAlarmAtLocation(double latitude, double longitude, double radius) {
        GeoCircle area = new GeoCircle(latitude, longitude, radius);
        addAlarmInteractor.addAlarmAtLocation(area);
    }
}
