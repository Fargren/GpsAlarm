package epsz.alarmapp;

import android.app.Application;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AlarmsTest {

    public ApplicationTest() {
        super(Application.class);
    }


    public void testShowNoAlarm() {
        FakePresenter mockPresenter = getFakePresenter();
        interactors.getShowAlarmInteractor().show();
        assertTrue(mockPresenter.shownAlarms.isEmpty());
    }

    public void testShowOneAlarmShowsOneAlarm() {
        FakePresenter mockPresenter = getFakePresenter();

        addEmptyAlarm();

        interactors.getShowAlarmInteractor().show();
        assertEquals(1, mockPresenter.shownAlarms.size());
    }

    public void testShowOneAlarmShowsCorrectAlarm() {
        FakePresenter mockPresenter = getFakePresenter();

        Alarm alarm = addEmptyAlarm();

        interactors.getShowAlarmInteractor().show();
        assertEquals(alarm, mockPresenter.shownAlarms.get(0));
    }

    public void testTimeAlarmRingsAtTime() {
        FakePresenter mockPresenter = getFakePresenter();

        createAlarmAtTime(9, 00);

        updateTimeTo(9, 00);
        assertTrue(mockPresenter.ringAlarmCalled);
    }

    public void testTimeAlarmDoesNotRingOutOfTime() {
        FakePresenter mockPresenter = getFakePresenter();

        createAlarmAtTime(8, 00);

        updateTimeTo(9, 00);
        assertFalse(mockPresenter.ringAlarmCalled);
    }

    public void testStopAlarm() {
        FakePresenter mockPresenter = getFakePresenter();

        interactors.stopAlarmInteractor.stop();
        assertTrue(mockPresenter.stopAlarmCalled);
    }

    public void testLocationAlarmRingsAtLocation() {
        FakePresenter mockPresenter = getFakePresenter();

        createAlarmAtLocation(50.5, 50.5, 0.001);

        updateLocationTo(50.5001, 50.5, 0.001);
        assertTrue(mockPresenter.ringAlarmCalled);
    }


    public void testLocationAlarmDoesNotRingOutsideLocation() {
        FakePresenter mockPresenter = getFakePresenter();

        createAlarmAtLocation(50.5, 50.5, 0.001);

        updateLocationTo(50.5, -50.5, 0.001);
        assertFalse(mockPresenter.ringAlarmCalled);
    }

    private void updateLocationTo(double longitude, double latitude, double radius) {
        LocationTrigger activeTrigger = new LocationTrigger(latitude, longitude, radius);
        interactors.getUpdateInteractor().updateTo(activeTrigger);
    }

    private void updateTimeTo(int hour, int minutes) {
        TimeTrigger activeTrigger = new TimeTrigger(hour, minutes);
        interactors.getUpdateInteractor().updateTo(activeTrigger);
    }

}