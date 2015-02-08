package epsz.alarmapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.List;

import epsz.alarmapp.Interactors.DataStore;
import epsz.alarmapp.Interactors.Interactors;
import epsz.alarmapp.requests.HourTime;

public class AlarmsTest extends ApplicationTestCase<Application> {
    protected Interactors interactors;
    protected FakeDataStore mockDataStore;
    protected FakePresenter mockPresenter;

    public AlarmsTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    protected void createAlarmAtTime(int hour, int minutes) {
        HourTime time = new HourTime(hour, minutes);
        interactors.getAddAlarmInteractor().addAlarmAtTime(time);
    }

    private void preparePresenters(FakePresenter presenter) {
        interactors.getAddAlarmInteractor().setPresenter(presenter);
        interactors.getShowAlarmInteractor().setPresenter(presenter);
        interactors.getUpdateInteractor().setPresenter(presenter);
        interactors.getStopAlarmInteractor().setPresenter(presenter);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockDataStore =  new FakeDataStore();
        interactors = new Interactors(mockDataStore);
        mockPresenter = new FakePresenter();
        preparePresenters(mockPresenter);
    }

    protected GeoCircle alarmAtLocation(double latitude, double longitude, double radius) {
        GeoCircle area = new GeoCircle(latitude, longitude, radius);
        interactors.getAddAlarmInteractor().addAlarmAtLocation(area);
        return area;
    }

    protected class FakePresenter implements Presenter {
        public Alarm addedAlarm;
        public List<GeoCircle> shownAlarms;
        public boolean stopAlarmCalled;
        public GeoCircle lastAlarmCircle;
        public int alarmRingCount;

        @Override
        public void ringAlarm() {
            alarmRingCount++;
        }

        @Override
        public void addAlarmAtLocation(GeoCircle location) {
            lastAlarmCircle = location;
        }

        @Override
        public void showAlarms(List<GeoCircle> alarms) {
            shownAlarms = alarms;
        }

        @Override
        public void stopAlarm() {
            stopAlarmCalled = true;
        }


    }

    protected class FakeDataStore implements DataStore {
        public GeoCircle lastAlarm;
        public ArrayList<GeoCircle> alarms;

        public FakeDataStore() {
            alarms =  new ArrayList<>();
        }

        @Override
        public void addAlarm(GeoCircle area) {
            lastAlarm = area;

            alarms.add(area);
        }

        @Override
        public List<GeoCircle> getAlarms() {
            return alarms;
        }
    }
}
