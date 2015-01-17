package epsz.alarmapp.Interactors;

import java.util.ArrayList;
import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.LocationTrigger;
import epsz.alarmapp.Presenter;
import epsz.alarmapp.TimeTrigger;
import epsz.alarmapp.Trigger;
import epsz.alarmapp.requests.HourTime;

/**
 * Created by Martin on 12/27/2014.
 */
public class AddAlarmInteractor implements AlarmAdder {
    private Presenter presenter;
    public static List<Trigger> triggers;


    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
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

        if(triggers == null)
            triggers = new ArrayList<>();

        triggers.add(new LocationTrigger(area));
        /*LocationTrigger trigger = new LocationTrigger(area);
        Alarm alarm = new Alarm();
        alarm.addTrigger(trigger);
        alarms.add(alarm);*/

        this.presenter.addAlarmAtLocation(area);
    }
}
