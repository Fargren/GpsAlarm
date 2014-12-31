package epsz.alarmapp.Interactors;

import java.util.ArrayList;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.Presenter;
import epsz.alarmapp.TimeTrigger;

public class UpdateStateInteractor {

    private final ArrayList<Alarm> alarms;
    private Presenter presenter;

    public UpdateStateInteractor(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void updateTo(TimeTrigger time) {
        for (Alarm alarm : alarms) {
            if (alarm.shouldRing(time)) {
                presenter.ringAlarm();
            }
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
