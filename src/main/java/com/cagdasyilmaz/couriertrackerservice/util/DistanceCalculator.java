package com.cagdasyilmaz.couriertrackerservice.util;

public class DistanceCalculator {
    private static final int EARTH_RADIUS = 6371;

    /**
     * This method calculates the distance between two geolocations
     * using haversine formula.
     *
     * @param firstLatitude First location's latitude
     * @param firstLongitude First location's longitude
     * @param secondLatitude Second location's latitude
     * @param secondLongitude Second location's latitude
     * @return Distance value between two geolocations
     */
    public static double calculateDistance(double firstLatitude, double firstLongitude,
                                           double secondLatitude, double secondLongitude) {
        double latitudeDistance = Math.toRadians(secondLatitude - firstLatitude);
        double longitudeDistance = Math.toRadians(secondLongitude - firstLongitude);
        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(firstLatitude)) * Math.cos(Math.toRadians(secondLatitude))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c * 1000; // convert to meters
    }
}
