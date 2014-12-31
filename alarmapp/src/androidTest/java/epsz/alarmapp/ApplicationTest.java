package epsz.alarmapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import epsz.alarmapp.Interactors.Interactors;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private Interactors interactors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interactors = new Interactors();
    }

    public ApplicationTest() {
        super(Application.class);
    }

    public void testAddAlarm() {
        FakePresenter mockPresenter = getFakePresenter();
        Alarm alarm = addEmptyAlarm();
        assertEquals(alarm, mockPresenter.addedAlarm);
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

    public void testTimeAlarmDoesntRingsOutOfTime() {
        FakePresenter mockPresenter = getFakePresenter();

        createAlarmAt(8, 00);

        updateTimeTo(9, 00);
        assertFalse(mockPresenter.ringAlarmCalled);
    }

    private void updateTimeTo(int hour, int minutes) {
        TimeTrigger activeTrigger = new TimeTrigger(hour, minutes);
        interactors.getUpdateInteractor().updateTo(activeTrigger);
    }

    private void createAlarmAt(int hour, int minutes) {
        Alarm alarm = new Alarm();
        TimeTrigger passiveTrigger = new TimeTrigger(hour, minutes);
        alarm.addTrigger(passiveTrigger);
        interactors.getAddAlarmInteractor().addAlarm(alarm);
    }

    private FakePresenter getFakePresenter() {
        FakePresenter presenter = new FakePresenter();
        preparePresenters(presenter);
        return presenter;
    }

    private void preparePresenters(FakePresenter presenter) {
        interactors.getAddAlarmInteractor().setPresenter(presenter);
        interactors.getShowAlarmInteractor().setPresenter(presenter);
        interactors.getUpdateInteractor().setPresenter(presenter);
    }

    private Alarm addEmptyAlarm() {
        Alarm alarm = new Alarm();
        interactors.getAddAlarmInteractor().addAlarm(alarm);
        return alarm;
    }

    private class FakePresenter implements Presenter {
        public boolean ringAlarmCalled;
        public Alarm addedAlarm;
        public List<Alarm> shownAlarms;

        @Override
        public void ringAlarm() {
            ringAlarmCalled = true;
        }

        @Override
        public void addAlarm(Alarm alarm) {
            addedAlarm = alarm;
        }

        @Override
        public void showAlarms(List<Alarm> alarms) {
            shownAlarms = alarms;
        }
    }
}