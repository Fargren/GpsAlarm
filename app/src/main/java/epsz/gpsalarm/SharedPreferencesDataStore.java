package epsz.gpsalarm;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import epsz.alarmapp.GeoCircle;
import epsz.alarmapp.Interactors.DataStore;

public class SharedPreferencesDataStore implements DataStore {


    private List<GeoCircle> alarms;
    private SharedPreferences sharedPreferences;
    private final Gson gson;

    public SharedPreferencesDataStore(SharedPreferences sharedPreferences) {
        gson = new Gson();
        this.sharedPreferences = sharedPreferences;
        alarms = getAlarms();
    }

    @Override
    public void addAlarm(GeoCircle area) {
        if (area != null)
            alarms.add(area);

        SharedPreferences.Editor alarmSaver = sharedPreferences.edit();
        alarmSaver.putString("alarms", gson.toJson(alarms));
        alarmSaver.apply();
    }

    @Override
    public List<GeoCircle> getAlarms() {
        String savedAlarms = sharedPreferences.getString("alarms", null);
        if (savedAlarms != null) {
            Type collectionType = new TypeToken<Collection<GeoCircle>>(){}.getType();
            return gson.fromJson(savedAlarms, collectionType);
        } else
            return new ArrayList<>();
    }
}
