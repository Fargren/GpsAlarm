package epsz.alarmapp;

import static epsz.alarmapp.LocationTrigger.InvalidGeoAreaException;

public class GeoCircle {
    public double latitude;
    public double longitude;
    public double radius;

    public GeoCircle(double latitude, double longitude, double radius) {
        if (outOfGeographicBounds(latitude, longitude) || radius <= 0) {
            throw new InvalidGeoAreaException();
        }

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    private boolean outOfGeographicBounds(double latitude, double longitude) {
        return latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180;
    }
}
