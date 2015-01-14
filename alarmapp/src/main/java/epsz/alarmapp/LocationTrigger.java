package epsz.alarmapp;

import epsz.alarmapp.Interactors.GeoCircle;

public class LocationTrigger extends Trigger {
    private final double latitude;
    private final double longitude;
    private final double radius;

    public LocationTrigger(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public LocationTrigger(GeoCircle area) {
        this.latitude = area.latitude;
        this.longitude = area.longitude;
        this.radius = area.radius;
    }

    @Override
    public boolean matches(Trigger trigger) {
        if (trigger instanceof LocationTrigger) {
            LocationTrigger locationTrigger = (LocationTrigger) trigger;
            double yDistance = this.latitude - locationTrigger.latitude;
            double xDistance = this.longitude - locationTrigger.longitude;
            double distanceSquared = xDistance * xDistance + yDistance * yDistance;
            double maxDistance = this.radius + locationTrigger.radius;
            return distanceSquared < maxDistance * maxDistance;
        }
        return false;
    }

    public static class InvalidGeoAreaException extends RuntimeException {
    }
}
