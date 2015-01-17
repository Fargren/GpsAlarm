package epsz.alarmapp.Interactors;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.LocationTrigger;
import epsz.alarmapp.Presenter;
import epsz.alarmapp.TimeTrigger;
import epsz.alarmapp.requests.HourTime;

/**
 * Created by Martin on 12/27/2014.
 */
public class AddAlarmInteractor implements AlarmAdder {
    private Presenter presenter;
    private List<Alarm> alarms;

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    AddAlarmInteractor() {

    }

    public void addAlarmAtTime(HourTime time) {
        TimeTrigger trigger = new TimeTrigger(time);
        Alarm alarm = new Alarm();
        alarm.addTrigger(trigger);
        alarms.add(alarm);

        this.presenter.addAlarm(alarm);
    }

    public void addAlarmAtLocation(GeoCircle area) {
        if (area == null) {
            return;
        }
        /*LocationTrigger trigger = new LocationTrigger(area);
        Alarm alarm = new Alarm();
        alarm.addTrigger(trigger);
        alarms.add(alarm);*/

        this.presenter.addAlarmAtLocation(area);
    }
}
