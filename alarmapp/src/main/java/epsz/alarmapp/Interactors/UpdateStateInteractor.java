package epsz.alarmapp.Interactors;

import java.util.ArrayList;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.Presenter;
import epsz.alarmapp.Trigger;

public class UpdateStateInteractor {

    private final ArrayList<Alarm> alarms;
    private Presenter presenter;

    public UpdateStateInteractor(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void updateTo(Trigger trigger) {
        for (Alarm alarm : alarms) {
            alarm.updateTo(trigger);
            if (alarm.isRinging) {
                presenter.ringAlarm();
            }
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
