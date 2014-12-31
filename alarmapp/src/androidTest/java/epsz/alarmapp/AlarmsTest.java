package epsz.alarmapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import epsz.alarmapp.Interactors.Interactors;

public class AlarmsTest extends ApplicationTestCase<Application> {
    protected Interactors interactors;

    public AlarmsTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    public void testAddAlarm() {
        FakePresenter mockPresenter = getFakePresenter();
        Alarm alarm = addEmptyAlarm();
        assertEquals(alarm, mockPresenter.addedAlarm);
    }

    protected void createAlarmAt(int hour, int minutes) {
        Alarm alarm = new Alarm();
        TimeTrigger passiveTrigger = new TimeTrigger(hour, minutes);
        alarm.addTrigger(passiveTrigger);
        interactors.getAddAlarmInteractor().addAlarm(alarm);
    }

    protected FakePresenter getFakePresenter() {
        FakePresenter presenter = new FakePresenter();
        preparePresenters(presenter);
        return presenter;
    }

    private void preparePresenters(FakePresenter presenter) {
        interactors.getAddAlarmInteractor().setPresenter(presenter);
        interactors.getShowAlarmInteractor().setPresenter(presenter);
        interactors.getUpdateInteractor().setPresenter(presenter);
        interactors.stopAlarmInteractor.setPresenter(presenter);
    }

    protected Alarm addEmptyAlarm() {
        Alarm alarm = new Alarm();
        interactors.getAddAlarmInteractor().addAlarm(alarm);
        return alarm;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interactors = new Interactors();
    }

    protected class FakePresenter implements Presenter {
        public boolean ringAlarmCalled;
        public Alarm addedAlarm;
        public List<Alarm> shownAlarms;
        public boolean stopAlarmCalled;

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

        @Override
        public void stopAlarm() {
            stopAlarmCalled = true;
        }


    }
}
