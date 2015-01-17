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
            LocationTrigger locTrigger = (LocationTrigger) trigger;
            double distance = distFrom(locTrigger.latitude, locTrigger.longitude, latitude, longitude);
            return distance < radius + locTrigger.radius;
        }
        return false;
    }

    public static class InvalidGeoAreaException extends RuntimeException {
    }

    public static float distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371; //kilometers
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return (float) (earthRadius * c) * 1000;
    }
}
