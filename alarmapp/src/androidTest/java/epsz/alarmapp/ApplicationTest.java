package epsz.alarmapp;

import static epsz.alarmapp.LocationTrigger.InvalidGeoAreaException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AlarmsTest {
    public ApplicationTest() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_addNullAlarmAtLocation_isntShown() {
        interactors.getAddAlarmInteractor().addAlarmAtLocation(null);
        assertNull(mockPresenter.addedAlarm);
    }

    public void test_outOfBoundsException_throws() {
        boolean isInvalid = circleAreaIsInvalid(100, 0 ,1);
        assertTrue(isInvalid);
        isInvalid = circleAreaIsInvalid(-100, 0 ,1);
        assertTrue(isInvalid);
        isInvalid = circleAreaIsInvalid(0, 200, 1);
        assertTrue(isInvalid);
        isInvalid = circleAreaIsInvalid(0, -200, 1);
        assertTrue(isInvalid);
    }

    public void test_negativeRadius_throws() {
        boolean isInvalid = circleAreaIsInvalid(0, 0, -1);
        assertTrue(isInvalid);
    }

    public void test_noRadius_throws() {
        boolean isInvalid = circleAreaIsInvalid(0, 0, 0);
        assertTrue(isInvalid);
    }

    public void test_addAlarm_addsCorrectAlarm() {
        GeoCircle area = alarmAtLocation(10, -10, 1);
        assertEquals(mockPresenter.lastAlarmCircle, area);
    }

    public void test_addAlarm_isStored() {
        GeoCircle area = alarmAtLocation(10, -10, 1);
        assertEquals(mockDataStore.lastAlarm, area);
    }

    private boolean circleAreaIsInvalid(double latitude, double longitude, double radius) {
        boolean thrown = false;
        try {
            new GeoCircle(latitude, longitude, radius);
        } catch (InvalidGeoAreaException e) {
            thrown = true;
        }
        return thrown;
    }

    public void testShowNoAlarm() {
    }

    public void testTimeAlarmRingsAtTime() {
        /*createAlarmAtTime(9, 00);

        updateTimeTo(9, 00);
        assertTrue(mockPresenter.ringAlarmCalled);*/
    }

    public void testTimeAlarmDoesNotRingOutOfTime() {
        /*createAlarmAtTime(8, 00);

        updateTimeTo(9, 00);
        assertFalse(mockPresenter.ringAlarmCalled);*/
    }

    public void test_stopAlarm_stopsAlarm() {
        interactors.getStopAlarmInteractor().stop();
        assertTrue(mockPresenter.stopAlarmCalled);
    }


}