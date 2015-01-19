package epsz.gpsalarm;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import epsz.alarmapp.Interactors.AlarmAdder;
import epsz.alarmapp.Interactors.GeoCircle;
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
        FakeController mockAlarmAdder = new FakeController();
        controller.addAlarmInteractor = mockAlarmAdder;
        controller.addAlarmAtLocation(10, -10, 1);
        assertReflectionEquals(mockAlarmAdder.lastAlarmArea, new GeoCircle(10, -10, 1));
    }

    public void test_presentAddAlarmAtLocation_presentsAlarm() {
        FakeMap mockMap = new FakeMap();
        MapActivityPresenter presenter = new MapActivityPresenter(mockMap);
        presenter.addAlarmAtLocation(new GeoCircle(10, -10, 1));
        assertReflectionEquals(mockMap.options.getCenter(), new LatLng(10, -10));
        assertEquals(mockMap.options.getRadius(), 1.0);
    }

    public void test_updateState_callsUseCase() {
        MapActivityController controller = new MapActivityController();
        FakeController mockLocationUpdater = new FakeController();
        controller.updateStateInteractor = mockLocationUpdater;
        controller.updateToLocation(10.0, -10.0, 1.0);
        assertReflectionEquals(mockLocationUpdater.lastUpdateArea, new GeoCircle(10, -10, 1));
    }

    private class FakeController implements AlarmAdder, LocationUpdater {
        public GeoCircle lastAlarmArea;
        public GeoCircle lastUpdateArea;

        @Override
        public void addAlarmAtLocation(GeoCircle area){
            lastAlarmArea = area;
        }

        @Override
        public void updateTo(GeoCircle area){
            lastUpdateArea = area;
        }
    }


    private class FakeMap implements Map {
        CircleOptions options;

        @Override
        public void addAlarmCircle(CircleOptions options) {
            this.options = options;
        }

    }
}