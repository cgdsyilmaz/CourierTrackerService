package com.cagdasyilmaz.couriertrackerservice.location.util;

public class LocationValidationConstraints {
    public static final String COURIER_NAME_BLANK_MESSAGE = "Courier must have a name!";
    public static final String COURIER_EMAIL_NOT_VALID_MESSAGE = "Not a valid email address!";
    public static final String COURIER_LATITUDE_NULL_MESSAGE = "Latitude information is missing!";
    public static final String COURIER_LONGITUDE_NULL_MESSAGE = "Longitude information is missing!";
    public static final String COURIER_UPDATE_TIME_NULL_MESSAGE = "Location update time must be present!";
}
