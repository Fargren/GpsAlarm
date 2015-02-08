package epsz.alarmapp.Interactors;

public class ShowAlarmsInteractor extends Interactor {
    public ShowAlarmsInteractor(DataStore dataStore) {
        super(dataStore);
    }

    public void show() {
        presenter.showAlarms(dataStore.getAlarms());
    }
}
