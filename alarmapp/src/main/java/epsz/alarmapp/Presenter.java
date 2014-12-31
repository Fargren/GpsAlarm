package epsz.alarmapp;

import java.util.List;

public interface Presenter {

    public void ringAlarm();

    void addAlarm(Alarm alarm);

    void showAlarms(List<Alarm> alarms);

    void stopAlarm();
}
