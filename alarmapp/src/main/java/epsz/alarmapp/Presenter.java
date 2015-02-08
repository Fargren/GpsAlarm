package epsz.alarmapp;

import java.util.List;

public interface Presenter {

    public void ringAlarm();

    void addAlarmAtLocation(GeoCircle location);

    void showAlarms(List<GeoCircle> alarms);

    void stopAlarm();
}
