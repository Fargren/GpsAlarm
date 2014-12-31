package epsz.alarmapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import epsz.alarmapp.Interactors.Interactors;

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

        createAlarmAt(9, 00);

        updateTimeTo(9, 00);
        assertTrue(mockPresenter.ringAlarmCalled);
    }

    public void testTimeAlarmDoesNotRingOutOfTime() {
        FakePresenter mockPresenter = getFakePresenter();

        createAlarmAt(8, 00);

        updateTimeTo(9, 00);
        assertFalse(mockPresenter.ringAlarmCalled);
    }

    public void testStopAlarm() {
        FakePresenter mockPresenter = getFakePresenter();

        interactors.stopAlarmInteractor.stop();
        assertTrue(mockPresenter.stopAlarmCalled);
    }

    private void updateTimeTo(int hour, int minutes) {
        TimeTrigger activeTrigger = new TimeTrigger(hour, minutes);
        interactors.getUpdateInteractor().updateTo(activeTrigger);
    }

}