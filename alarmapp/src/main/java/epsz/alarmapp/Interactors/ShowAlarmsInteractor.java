package epsz.alarmapp.Interactors;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.Presenter;

public class ShowAlarmsInteractor {
    private Presenter presenter;
    private List<Alarm> alarms;

    ShowAlarmsInteractor() {

    }

    public void show() {
        this.presenter.showAlarms(alarms);
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
