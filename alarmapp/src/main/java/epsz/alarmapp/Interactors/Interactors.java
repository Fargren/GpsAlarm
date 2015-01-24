package epsz.alarmapp.Interactors;

import java.util.ArrayList;
import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.LocationTrigger;

public class Interactors {
    private final List<Alarm> alarms;

    private AddAlarmInteractor addAlarmInteractor;
    private ShowAlarmsInteractor showAlarmInteractor;
    private UpdateStateInteractor updateInteractor;
    private StopAlarmInteractor stopAlarmInteractor;

    private DataStore dataStore;

    public Interactors(DataStore dataStore) {
        this.dataStore = dataStore;

        alarms = new ArrayList<>();
        for (GeoCircle area : dataStore.getAlarms())
            alarms.add(Alarm.createWithTrigger(new LocationTrigger(area)));

        createAddAlarmInteractor();
        createAddAlarmInteractor();
        createShowAlarmsInteractor();
        createUpdateStateInteractor();
        this.stopAlarmInteractor = new StopAlarmInteractor();
    }

    private void createUpdateStateInteractor() {
        this.updateInteractor = new UpdateStateInteractor(dataStore);
        this.updateInteractor.setAlarms(alarms);
    }

    private void createAddAlarmInteractor() {
        this.addAlarmInteractor = new AddAlarmInteractor(dataStore);
        this.addAlarmInteractor.setAlarms(alarms);
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

    public StopAlarmInteractor getStopAlarmInteractor() {
        return stopAlarmInteractor;
    }
}
