package epsz.alarmapp.Interactors;

import java.util.List;

import epsz.alarmapp.Alarm;
import epsz.alarmapp.GeoCircle;
import epsz.alarmapp.LocationTrigger;

public class UpdateStateInteractor extends Interactor implements LocationUpdater {

    private List<Alarm> alarms;
    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public UpdateStateInteractor(DataStore dataStore) {
        super(dataStore);
    }

    @Override
    public void updateTo(GeoCircle circle) {
        if(circle == null)
            return;

        for(Alarm alarm : alarms)
            updateAlarm(alarm, new LocationTrigger(circle));
    }

    private void updateAlarm(Alarm alarm, LocationTrigger trigger) {
        boolean wasRinging = alarm.isRinging;
        alarm.updateTo(trigger);
        if(!wasRinging && alarm.isRinging)
            presenter.ringAlarm();
    }

}
