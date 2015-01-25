package epsz.alarmapp.Interactors;

import epsz.alarmapp.Presenter;

public class StopAlarmInteractor extends Interactor implements AlarmStopper {
    private Presenter presenter;

    public StopAlarmInteractor(DataStore dataStore) {
        super(dataStore);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void stop() {
        this.presenter.stopAlarm();
    }
}
