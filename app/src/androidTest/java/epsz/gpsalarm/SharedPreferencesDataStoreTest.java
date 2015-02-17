package epsz.gpsalarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import junit.framework.TestCase;

import epsz.alarmapp.GeoCircle;

import static android.content.Context.MODE_PRIVATE;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class SharedPreferencesDataStoreTest extends AndroidTestCase {
    private static final GeoCircle DEFAULT_AREA = new GeoCircle(1, 2, 3)
            ;
    private SharedPreferencesDataStore store;
    private SharedPreferences sharedPreferences;

    public void setUp() throws Exception {
        super.setUp();
        sharedPreferences = getContext().getSharedPreferences("default", MODE_PRIVATE);
        store = new SharedPreferencesDataStore(sharedPreferences);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        sharedPreferences.edit().clear().apply();
    }

    public void test_addNullAlarm_isNotAdded() {
        store.addAlarm(null);
        assertEquals(0, store.getAlarms().size());
    }

    public void test_addAlarm_alarmIsAdded() {
        store.addAlarm(DEFAULT_AREA);

        assertEquals(1, store.getAlarms().size());
        assertReflectionEquals(DEFAULT_AREA, store.getAlarms().get(0));
    }

    public void test_add_alarm_secondDataStoreHasAlarm() {
        store.addAlarm(DEFAULT_AREA);
        SharedPreferencesDataStore secondStore = new SharedPreferencesDataStore(sharedPreferences);
        assertEquals(1, secondStore.getAlarms().size());
        assertReflectionEquals(DEFAULT_AREA, secondStore.getAlarms().get(0));
    }
}
