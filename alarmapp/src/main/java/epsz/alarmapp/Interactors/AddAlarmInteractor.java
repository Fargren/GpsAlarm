package epsz.alarmapp.Interactors;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.Presenter;

/**
 * Created by Martin on 12/27/2014.
 */
public class AddAlarmInteractor {
    private Presenter presenter;
    private List<Alarm> alarms;

    AddAlarmInteractor() {

    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);

        this.presenter.addAlarm(alarm);
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
