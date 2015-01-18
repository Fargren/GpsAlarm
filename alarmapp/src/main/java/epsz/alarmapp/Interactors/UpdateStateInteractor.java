package epsz.alarmapp.Interactors;

import epsz.alarmapp.LocationTrigger;
import epsz.alarmapp.Trigger;

public class UpdateStateInteractor extends Interactor {

    public UpdateStateInteractor(DataStore dataStore) {
        super(dataStore);
    }

    public void updateTo(Trigger activeTrigger) {
        for (GeoCircle area : dataStore.getAlarms())
            if (activeTrigger.matches(new LocationTrigger(area)))
                presenter.ringAlarm();
    }
}
