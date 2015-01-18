package epsz.gpsalarm;

import java.util.ArrayList;
import java.util.List;

import epsz.alarmapp.Interactors.DataStore;
import epsz.alarmapp.Interactors.GeoCircle;

public class ListDataStore implements DataStore {
    public GeoCircle lastAlarm;
    public ArrayList<GeoCircle> alarms;

    @Override
    public void addAlarm(GeoCircle area) {
        lastAlarm = area;

        if (alarms == null)
            alarms =  new ArrayList<>();
        alarms.add(area);
    }

    @Override
    public List<GeoCircle> getAlarms() {
        return alarms;
    }
}
