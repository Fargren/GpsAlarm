package epsz.alarmapp.Interactors;

import epsz.alarmapp.LocationTrigger;
import epsz.alarmapp.Trigger;

public class UpdateStateInteractor extends Interactor implements LocationUpdater {

    public UpdateStateInteractor(DataStore dataStore) {
        super(dataStore);
    }

    @Override
    public void updateTo(GeoCircle circle) {
        LocationTrigger trigger = new LocationTrigger(circle);
        for (GeoCircle area : dataStore.getAlarms())
            if (trigger.matches(new LocationTrigger(area)))
                presenter.ringAlarm();
    }

}
