package epsz.alarmapp.Interactors;

import epsz.alarmapp.Presenter;

public class Interactor {
    protected Presenter presenter;
    protected DataStore dataStore;

    public Interactor(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setDataStore(DataStore datastore) {
        this.dataStore = datastore;
    }
}
