package com.cagdasyilmaz.couriertrackerservice.courier.exception;

public class CourierAlreadyEmployedException extends CourierException {
    public CourierAlreadyEmployedException(String email) {
        super("Courier with email " + email + " is already employed!");
    }
}
