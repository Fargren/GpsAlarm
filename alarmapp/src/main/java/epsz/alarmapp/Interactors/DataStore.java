package epsz.alarmapp.Interactors;

import java.util.List;

import epsz.alarmapp.GeoCircle;

public interface DataStore {
    void addAlarm(GeoCircle area);

    List<GeoCircle> getAlarms();
}
