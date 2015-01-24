package epsz.alarmapp.Interactors;

import java.util.List;

public interface DataStore {
    void addAlarm(GeoCircle area);

    List<GeoCircle> getAlarms();
}
