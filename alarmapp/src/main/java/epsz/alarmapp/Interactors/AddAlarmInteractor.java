package epsz.alarmapp.Interactors;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.GeoCircle;
import epsz.alarmapp.LocationTrigger;
import epsz.alarmapp.requests.HourTime;

public class AddAlarmInteractor extends Interactor implements AlarmAdder {

    private List<Alarm> alarms;
    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public AddAlarmInteractor(DataStore dataStore) {
        super(dataStore);
    }

    public void addAlarmAtTime(HourTime time) {
    }

    public void addAlarmAtLocation(GeoCircle area) {
        if (area == null) {
            return;
        }

        dataStore.addAlarm(area);
        alarms.add(Alarm.createWithTrigger(new LocationTrigger(area)));

        this.presenter.addAlarmAtLocation(area);
    }

}
