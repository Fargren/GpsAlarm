package epsz.gpsalarm;

import java.util.ArrayList;
import java.util.List;

import epsz.alarmapp.Interactors.DataStore;
import epsz.alarmapp.GeoCircle;

public class ListDataStore implements DataStore {
    private ArrayList<GeoCircle> alarms;

    public ListDataStore() {
        alarms =  new ArrayList<>();
    }

    @Override
    public void addAlarm(GeoCircle area) {
        if (area != null)
            alarms.add(area);
    }

    @Override
    public List<GeoCircle> getAlarms() {
        return alarms;
    }
}
