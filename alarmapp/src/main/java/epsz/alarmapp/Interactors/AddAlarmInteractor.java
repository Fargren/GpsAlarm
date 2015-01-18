package epsz.alarmapp.Interactors;

import epsz.alarmapp.requests.HourTime;

/**
 * Created by Martin on 12/27/2014.
 */
public class AddAlarmInteractor extends Interactor implements AlarmAdder {

    public AddAlarmInteractor(DataStore dataStore) {
        super(dataStore);
    }

    public void addAlarmAtTime(HourTime time) {
        /*TimeTrigger trigger = new TimeTrigger(time);
        Alarm alarm = new Alarm();
        alarm.addTrigger(trigger);
        alarms.add(alarm);

        this.presenter.addAlarm(alarm);*/
    }

    public void addAlarmAtLocation(GeoCircle area) {
        if (area == null) {
            return;
        }

        dataStore.addAlarm(area);
        /*LocationTrigger trigger = new LocationTrigger(area);
        Alarm alarm = new Alarm();
        alarm.addTrigger(trigger);
        alarms.add(alarm);*/

        this.presenter.addAlarmAtLocation(area);
    }

}
