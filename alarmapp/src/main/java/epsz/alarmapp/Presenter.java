package epsz.alarmapp;

import java.util.List;

public interface Presenter {

    public void ringAlarm();

    void addAlarmAtLocation(GeoCircle location);

    void addAlarm(Alarm alarm);

    void showAlarms(List<Alarm> alarms);

    void stopAlarm();
}
