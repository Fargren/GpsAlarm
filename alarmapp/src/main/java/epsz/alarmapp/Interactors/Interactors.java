package epsz.alarmapp.Interactors;

import java.util.ArrayList;

import epsz.alarmapp.Alarm;

public class Interactors {
    private AddAlarmInteractor addAlarmInteractor;
    private ShowAlarmsInteractor showAlarmInteractor;
    private UpdateStateInteractor updateInteractor;

    public Interactors() {
        ArrayList<Alarm> alarms = new ArrayList<>();
        createAddAlarmInteractor(alarms);
        createShowAlarmsInteractor(alarms);
        createUpdateStateInteractor(alarms);
    }

    private void createUpdateStateInteractor(ArrayList<Alarm> alarms) {
        this.updateInteractor = new UpdateStateInteractor(alarms);
    }

    private void createAddAlarmInteractor(ArrayList<Alarm> alarms) {
        AddAlarmInteractor interactor = new AddAlarmInteractor();
        interactor.setAlarms(alarms);
        this.addAlarmInteractor = interactor;
    }

    private void createShowAlarmsInteractor(ArrayList<Alarm> alarms) {
        ShowAlarmsInteractor interactor = new ShowAlarmsInteractor();
        interactor.setAlarms(alarms);
        this.showAlarmInteractor = interactor;
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
