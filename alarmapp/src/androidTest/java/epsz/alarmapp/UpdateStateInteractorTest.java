package epsz.alarmapp;

import android.app.Application;

import epsz.alarmapp.Interactors.Interactors;

public class UpdateStateInteractorTest extends AlarmsTest {

    public UpdateStateInteractorTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        alarmAtLocation(-34.606150, -58.380679, 1000);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_updateToNullLocation_doesNotRing() {
        interactors.getUpdateInteractor().updateTo(null);
        assertEquals(mockPresenter.alarmRingCount, 0);
    }

    public void test_updateOutOfAlarms_doesNotRing() {
        updateLocationTo(-34.605284, -58.366624, 1);
        assertEquals(mockPresenter.alarmRingCount, 0);
    }

    public void test_updateMatchesAlarm_rings() {
        updateLocationTo(-34.606150, -58.380679, 1000);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_updateCloseToAlarm_rings() {
        updateLocationTo(-34.605355, -58.370508, 1);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_addTwoAlarmsOneMatches_rings() {
        alarmAtLocation(0, 0, 1000);

        updateLocationTo(-34.605355, -58.370508, 1);
        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_twiceAtAlarm_ringsOnce() {
        updateLocationTo(-34.605355, -58.370508, 1);
        updateLocationTo(-34.605355, -58.370508, 1);

        assertEquals(mockPresenter.alarmRingCount, 1);
    }

    public void test_secondInteracorsWtihSameData_rings() {
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
