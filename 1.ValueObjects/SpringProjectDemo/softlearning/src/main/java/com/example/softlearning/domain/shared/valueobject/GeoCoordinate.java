package com.example.softlearning.domain.shared.valueobject;

public final class GeoCoordinate {
    private final double latitude;
    private final double longitude;

    private GeoCoordinate(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Invalid latitude");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid longitude");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static GeoCoordinate of(double latitude, double longitude) throws IllegalArgumentException {
        return new GeoCoordinate(latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distanceTo(GeoCoordinate other) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(other.latitude - this.latitude);
        double lonDistance = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // convert to kilometers
    }  
    
    public GeoCoordinate move(double deltaLatitude, double deltaLongitude) throws IllegalArgumentException {
        return new GeoCoordinate(this.latitude + deltaLatitude, this.longitude + deltaLongitude);
    }

    @Override
    public String toString() {
        return "latitude=" + latitude + ", longitude=" + longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoCoordinate that = (GeoCoordinate) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(latitude);
        result = 31 * result + Double.hashCode(longitude);
        return result;
    }
}