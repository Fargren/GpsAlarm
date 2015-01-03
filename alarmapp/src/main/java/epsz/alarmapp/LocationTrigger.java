package epsz.alarmapp;

public class LocationTrigger extends Trigger {
    public LocationTrigger(double latitude, double longitude, double radius) {
    }

    @Override
    public boolean matches(Trigger trigger) {
        return true;
    }
}
