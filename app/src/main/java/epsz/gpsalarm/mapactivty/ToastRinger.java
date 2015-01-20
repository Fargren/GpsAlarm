package epsz.gpsalarm.mapactivty;

import android.content.Context;
import android.widget.Toast;

import epsz.gpsalarm.MapApplication;
import epsz.gpsalarm.Ringer;

public class ToastRinger implements Ringer {
    @Override
    public void ring() {
        Context context = MapApplication.getInstance().getBaseContext();
        Toast.makeText(context, "Alarm!", Toast.LENGTH_LONG).show();
    }
}
