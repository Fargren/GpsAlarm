package epsz.alarmapp;

import java.util.List;

import epsz.alarmapp.Interactors.GeoCircle;

public interface Presenter {

    public void ringAlarm();

    void addAlarmAtLocation(GeoCircle location);

    void addAlarm(Alarm alarm);

    void showAlarms(List<Alarm> alarms);

    void stopAlarm();
}
