package epsz.alarmapp.Interactors;

import java.util.ArrayList;

import epsz.alarmapp.Alarm;

public class Interactors {
    private AddAlarmInteractor addAlarmInteractor;
    private ShowAlarmsInteractor showAlarmInteractor;
    private UpdateStateInteractor updateInteractor;
    public StopAlarmInteractor stopAlarmInteractor;

    private DataStore dataStore;

    private ArrayList<Interactor> interactors;

    public Interactors(DataStore dataStore) {
        this.dataStore = dataStore;

        interactors = new ArrayList<>();
        createAddAlarmInteractor();
        createAddAlarmInteractor();
        createShowAlarmsInteractor();
        createUpdateStateInteractor();
        this.stopAlarmInteractor = new StopAlarmInteractor();
    }

    private void createUpdateStateInteractor() {
        this.updateInteractor = new UpdateStateInteractor(dataStore);
    }

    private void createAddAlarmInteractor() {
        this.addAlarmInteractor = new AddAlarmInteractor(dataStore);
    }

    private void createShowAlarmsInteractor() {
        this.showAlarmInteractor = new ShowAlarmsInteractor(dataStore);
    }

    public AddAlarmInteractor getAddAlarmInteractor() {
        return addAlarmInteractor;
    }

    public ShowAlarmsInteractor getShowAlarmInteractor() {
        return showAlarmInteractor;
    }

    public UpdateStateInteractor getUpdateInteractor() {
        return updateInteractor;
    }
}
