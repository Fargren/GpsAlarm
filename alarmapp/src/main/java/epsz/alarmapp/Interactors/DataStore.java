package epsz.alarmapp.Interactors;

import java.util.ArrayList;

import epsz.alarmapp.Trigger;

public interface DataStore {
    void addAlarm(GeoCircle area);

    java.util.List<GeoCircle> getAlarms();
}
