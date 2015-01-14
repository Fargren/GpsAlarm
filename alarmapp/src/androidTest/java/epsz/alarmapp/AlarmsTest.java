package epsz.alarmapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import epsz.alarmapp.Interactors.GeoCircle;
import epsz.alarmapp.Interactors.Interactors;
import epsz.alarmapp.requests.HourTime;

public class AlarmsTest extends ApplicationTestCase<Application> {
    protected Interactors interactors;

    public AlarmsTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    protected void createAlarmAtTime(int hour, int minutes) {
        HourTime time = new HourTime(hour, minutes);
        interactors.getAddAlarmInteractor().addAlarmAtTime(time);
    }

    protected FakePresenter getFakePresenter() {
        FakePresenter presenter = new FakePresenter();
        preparePresenters(presenter);
        return presenter;
    }

    private void preparePresenters(FakePresenter presenter) {
        interactors.getAddAlarmInteractor().setPresenter(presenter);
        interactors.getShowAlarmInteractor().setPresenter(presenter);
        interactors.getUpdateInteractor().setPresenter(presenter);
        interactors.stopAlarmInteractor.setPresenter(presenter);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interactors = new Interactors();
    }

    protected void createAlarmAtLocation(double latitude, double longitude, double radius) {
        GeoCircle area = new GeoCircle(latitude, longitude, radius);
        interactors.getAddAlarmInteractor().addAlarmAtLocation(area);
    }

    protected class FakePresenter implements Presenter {
        public boolean ringAlarmCalled;
        public Alarm addedAlarm;
        public List<Alarm> shownAlarms;
        public boolean stopAlarmCalled;
        public GeoCircle lastAlarmCircle;

        @Override
        public void ringAlarm() {
            ringAlarmCalled = true;
        }

        @Override
        public void addAlarmAtLocation(GeoCircle location) {
            lastAlarmCircle = location;
        }

        @Override
        public void addAlarm(Alarm alarm) {
            addedAlarm = alarm;
        }

        @Override
        public void showAlarms(List<Alarm> alarms) {
            shownAlarms = alarms;
        }

        @Override
        public void stopAlarm() {
            stopAlarmCalled = true;
        }


    }
}
