package epsz.gpsalarm;

import android.app.Application;
import android.content.SharedPreferences;

import epsz.alarmapp.Interactors.Interactors;

public class MapApplication extends Application {
    public static final String STORAGE = "map_alarm_storage";
    public static MapApplication instance;
    private Interactors interactors;

    public Interactors getInteractors() {
        return interactors;
    }

    public static MapApplication getInstance() {
        return instance;
    }

    public MapApplication() {
        super();
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs = getSharedPreferences(STORAGE, MODE_PRIVATE);
        interactors =  new Interactors(new SharedPreferencesDataStore(prefs));
    }
}
