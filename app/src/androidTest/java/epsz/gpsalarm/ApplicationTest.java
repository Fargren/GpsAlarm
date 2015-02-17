package epsz.gpsalarm;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import epsz.alarmapp.Interactors.AlarmAdder;
import epsz.alarmapp.Interactors.AlarmDisplayer;
import epsz.alarmapp.Interactors.AlarmStopper;
import epsz.alarmapp.GeoCircle;
import epsz.alarmapp.Interactors.LocationUpdater;
import epsz.gpsalarm.mapactivty.MapActivityController;
import epsz.gpsalarm.mapactivty.MapActivityPresenter;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test_addAlarm_callsUseCase() {
        MapActivityController controller = new MapActivityController();
        FakeInteractor mockAlarmAdder = new FakeInteractor();
        controller.addAlarmInteractor = mockAlarmAdder;
        controller.addAlarmAtLocation(10, -10, 1);
        assertReflectionEquals(mockAlarmAdder.lastAlarmArea, new GeoCircle(10, -10, 1));
    }

    public void test_presentAddAlarmAtLocation_presentsAlarm() {
        FakeMap mockMap = new FakeMap();
        MapActivityPresenter presenter = new MapActivityPresenter(mockMap, null);
        presenter.addAlarmAtLocation(new GeoCircle(10, -10, 1));
        assertEquals(1, mockMap.options.size());
        assertReflectionEquals(mockMap.options.get(0).getCenter(), new LatLng(10, -10));
        assertEquals(mockMap.options.get(0).getRadius(), 1.0);
    }

    public void test_updateState_callsUseCase() {
        MapActivityController controller = new MapActivityController();
        FakeInteractor mockLocationUpdater = new FakeInteractor();
        controller.updateStateInteractor = mockLocationUpdater;
        controller.updateToLocation(10.0, -10.0, 1.0);
        assertReflectionEquals(mockLocationUpdater.lastUpdateArea, new GeoCircle(10, -10, 1));
    }

    public void test_presentRingAlarm_presentsRing() {
        FakeRinger mockRinger = new FakeRinger();
        MapActivityPresenter presenter = new MapActivityPresenter(null, mockRinger);
        presenter.ringAlarm();
        assertTrue(mockRinger.rang);
    }

    public void test_stopAlarm_callsUseCase() {
        MapActivityController controller = new MapActivityController();
        FakeInteractor mockAlarmStopper = new FakeInteractor();
        controller.stopAlarmInteractor = mockAlarmStopper;
        controller.stopAlarm();
        assertTrue(mockAlarmStopper.alarmStopped);
    }

    public void test_stopRingAlarm_presentsStop() {
        FakeRinger mockRinger = new FakeRinger();
        MapActivityPresenter presenter = new MapActivityPresenter(null, mockRinger);
        presenter.stopAlarm();
        assertTrue(mockRinger.stopped);
    }

    public void test_showAlarms_callsUseCase() {
        MapActivityController controller = new MapActivityController();
        FakeInteractor mockAlarmShower = new FakeInteractor();
        controller.showAlarmsInteractor = mockAlarmShower;
        controller.refreshShownAlarms();
        assertTrue(mockAlarmShower.alarmsShown);
    }

    public void test_showAlarms_presentsAlarms() {
        FakeMap mockMap = new FakeMap();
        MapActivityPresenter presenter = new MapActivityPresenter(mockMap, null);
        ArrayList<GeoCircle> alarms = new ArrayList<>();
        alarms.add(new GeoCircle(10, -10, 1));
        alarms.add(new GeoCircle(20, -20, 2));
        presenter.showAlarms(alarms);
        assertEquals(2, mockMap.options.size());
        assertReflectionEquals(mockMap.options.get(0).getCenter(), new LatLng(10, -10));
        assertEquals(mockMap.options.get(0).getRadius(), 1.0);
        assertReflectionEquals(mockMap.options.get(1).getCenter(), new LatLng(20, -20));
        assertEquals(mockMap.options.get(1).getRadius(), 2.0);
    }

    private class FakeInteractor implements AlarmAdder, LocationUpdater, AlarmStopper, AlarmDisplayer {
        public GeoCircle lastAlarmArea;
        public GeoCircle lastUpdateArea;
        public boolean alarmStopped;
        public boolean alarmsShown;

        @Override
        public void addAlarmAtLocation(GeoCircle area){
            lastAlarmArea = area;
        }

        @Override
        public void updateTo(GeoCircle area){
            lastUpdateArea = area;
        }

        @Override
        public void stop() {
            alarmStopped = true;
        }

        @Override
        public void show() {
            alarmsShown = true;
        }
    }


    private class FakeMap implements Map {
        ArrayList<CircleOptions>options = new ArrayList<>();

        @Override
        public void addAlarmCircle(CircleOptions options) {
            this.options.add(options);
        }

    }

    private class FakeRinger implements Ringer {
        boolean rang;
        boolean stopped;

        @Override
        public void ring() {
            rang = true;
        }

        @Override
        public void stop() {
            stopped = true;
        }
    }
}