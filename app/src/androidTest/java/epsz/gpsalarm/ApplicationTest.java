package epsz.gpsalarm;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.unitils.reflectionassert.ReflectionAssert;

import epsz.alarmapp.Interactors.AddAlarmInteractor;
import epsz.alarmapp.Interactors.AlarmAdder;
import epsz.alarmapp.Interactors.GeoCircle;
import epsz.alarmapp.Interactors.Interactors;

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

    private class MockAlarmAdder implements AlarmAdder {
        public GeoCircle lastAlarmArea;

        @Override
        public void addAlarmAtLocation(GeoCircle area){
            lastAlarmArea = area;
        }
    }
}