package epsz.alarmapp.Interactors;

import epsz.alarmapp.Presenter;

public class StopAlarmInteractor implements AlarmStopper {
    private Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void stop() {
        this.presenter.stopAlarm();
    }
}
