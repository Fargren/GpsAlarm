package epsz.alarmapp;

import android.app.Application;

import epsz.alarmapp.Interactors.ShowAlarmsInteractor;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class ShowAlarmsInteractorsTest extends AlarmsTest {
    public ShowAlarmsInteractorsTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void test_showNoAlarms_showsNoAlarms() {
        ShowAlarmsInteractor interactor = interactors.getShowAlarmInteractor();
        interactor.setPresenter(mockPresenter);
        interactor.show();
        assertEquals(0, mockPresenter.shownAlarms.size());
    }

    public void test_showOneAlarm_showsCorrectAlarm() {
        ShowAlarmsInteractor interactor = interactors.getShowAlarmInteractor();
        interactor.setPresenter(mockPresenter);
        alarmAtLocation(1,2,3);
        interactor.show();
        assertEquals(1, mockPresenter.shownAlarms.size());
        assertReflectionEquals(new GeoCircle(1,2,3), mockPresenter.shownAlarms.get(0));
    }
}
