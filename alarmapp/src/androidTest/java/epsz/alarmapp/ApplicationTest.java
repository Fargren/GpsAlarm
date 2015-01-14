package epsz.alarmapp;

import android.app.Application;

import epsz.alarmapp.Interactors.GeoCircle;
import epsz.alarmapp.requests.HourTime;

import static epsz.alarmapp.LocationTrigger.InvalidGeoAreaException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AlarmsTest {

    private FakePresenter mockPresenter;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockPresenter = getFakePresenter();
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
        GeoCircle area = new GeoCircle(10, 10, 1);
        interactors.getAddAlarmInteractor().addAlarmAtLocation(area);
        assertEquals(mockPresenter.lastAlarmCircle, area);
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
        interactors.getShowAlarmInteractor().show();
        assertTrue(mockPresenter.shownAlarms.isEmpty());
    }



    public void testTimeAlarmRingsAtTime() {
        createAlarmAtTime(9, 00);

        updateTimeTo(9, 00);
        assertTrue(mockPresenter.ringAlarmCalled);
    }

    public void testTimeAlarmDoesNotRingOutOfTime() {
        createAlarmAtTime(8, 00);

        updateTimeTo(9, 00);
        assertFalse(mockPresenter.ringAlarmCalled);
    }

    public void testStopAlarm() {
        interactors.stopAlarmInteractor.stop();
        assertTrue(mockPresenter.stopAlarmCalled);
    }

    public void testLocationAlarmRingsAtLocation() {
       /* createAlarmAtLocation(50.5, 50.5, 0.001);

        updateLocationTo(50.5001, 50.5, 0.001);
        assertTrue(mockPresenter.ringAlarmCalled);*/
    }

    public void testLocationAlarmDoesNotRingOutsideLocation() {
        createAlarmAtLocation(50.5, 50.5, 0.001);

        updateLocationTo(50.5, -50.5, 0.001);
        assertFalse(mockPresenter.ringAlarmCalled);
    }

    private void updateLocationTo(double longitude, double latitude, double radius) {
        LocationTrigger activeTrigger = new LocationTrigger(latitude, longitude, radius);
        interactors.getUpdateInteractor().updateTo(activeTrigger);
    }

    private void updateTimeTo(int hour, int minutes) {
        HourTime time = new HourTime(hour, minutes);
        TimeTrigger activeTrigger = new TimeTrigger(time);
        interactors.getUpdateInteractor().updateTo(activeTrigger);
    }

}