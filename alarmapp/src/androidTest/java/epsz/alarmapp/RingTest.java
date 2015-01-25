package epsz.alarmapp;

import android.app.Application;

import epsz.alarmapp.Interactors.Interactors;

public class RingTest extends AlarmsTest {
    protected FakePresenter mockPresenter;

    public RingTest() {
        super(Application.class);
    }

    public RingTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockPresenter = getFakePresenter();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_updateToNullLocation_doesNotRing() {
        alarmAtLocation(10, -10, 1);
        interactors.getUpdateInteractor().updateTo(null);
        assertEquals(mockPresenter.alarmRingCount, 0);
    }

    public void test_updateOutOfAlarms_doesNotRing() {
        alarmAtLocation(-34.606150, -58.380679, 1000);

        updateLocationTo(-34.605284, -58.366624, 1);
        assertEquals(mockPresenter.alarmRingCount, 0);
    }

    public void test_updateMatchesAlarm_rings() {
        alarmAtLocation(10, -10, 1);

        updateLocationTo(10, -10, 1);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_updateCloseToAlarm_rings() {
        alarmAtLocation(-34.606150, -58.380679, 1000);

        updateLocationTo(-34.605355, -58.370508, 1);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_addTwoAlarmsOneMatches_rings() {
        alarmAtLocation(-34.606150, -58.380679, 1000);
        alarmAtLocation(0, 0, 1000);

        updateLocationTo(-34.605355, -58.370508, 1);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_twiceAtAlarm_ringsOnce() {
        alarmAtLocation(-34.606150, -58.380679, 1000);

        updateLocationTo(-34.605355, -58.370508, 1);
        updateLocationTo(-34.605355, -58.370508, 1);

        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_secondInteracorsWtihSameData_rings() {
        alarmAtLocation(-34.606150, -58.380679, 1000);

        Interactors newInteractors = new Interactors(mockDataStore);
        GeoCircle area = new GeoCircle(-34.605355, -58.370508, 1);
        newInteractors.getUpdateInteractor().setPresenter(mockPresenter);
        newInteractors.getUpdateInteractor().updateTo(area);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    private void updateLocationTo(double latitude, double longitude, double radius) {
        GeoCircle area = new GeoCircle(latitude, longitude, radius);
        interactors.getUpdateInteractor().updateTo(area);
    }

    private void updateTimeTo(int hour, int minutes) {
        /*HourTime time = new HourTime(hour, minutes);
        TimeTrigger activeTrigger = new TimeTrigger(time);
        interactors.getUpdateInteractor().updateTo(activeTrigger);*/
    }
}
