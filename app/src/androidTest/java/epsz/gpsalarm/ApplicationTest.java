package epsz.gpsalarm;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import epsz.alarmapp.Interactors.AlarmAdder;
import epsz.alarmapp.Interactors.GeoCircle;
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
        MockAlarmAdder adder = new MockAlarmAdder();
        controller.addAlarmInteractor = adder;
        controller.addAlarmAtLocation(10, -10, 1);
        assertReflectionEquals(adder.lastAlarmArea, new GeoCircle(10, -10, 1));
    }

    public void test_presentAddAlarmAtLocation_presentsAlarm() {
        FakeMap mockMap = new FakeMap();
        MapActivityPresenter presenter = new MapActivityPresenter(mockMap);
        presenter.addAlarmAtLocation(new GeoCircle(10, -10, 1));
        assertReflectionEquals(mockMap.options.getCenter(), new LatLng(10, -10));
        assertEquals(mockMap.options.getRadius(), 1.0);
    }

    private class MockAlarmAdder implements AlarmAdder {
        public GeoCircle lastAlarmArea;

        @Override
        public void addAlarmAtLocation(GeoCircle area){
            lastAlarmArea = area;
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