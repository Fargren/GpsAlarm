package epsz.gpsalarm;

import android.app.Application;

import epsz.alarmapp.Interactors.Interactors;

public class MapApplication extends Application {
    public static MapApplication instance;
    private final Interactors interactors;

    public Interactors getInteractors() {
        return interactors;
    }

    public static MapApplication getInstance() {
        return instance;
    }


    public MapApplication() {
        instance = this;

        this.interactors =  new Interactors();
    }
}
