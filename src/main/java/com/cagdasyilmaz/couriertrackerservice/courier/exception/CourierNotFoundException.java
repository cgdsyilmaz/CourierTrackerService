package com.cagdasyilmaz.couriertrackerservice.courier.exception;

import java.util.UUID;

public class CourierNotFoundException extends CourierException {
    public CourierNotFoundException(UUID courierId) {
        super("Courier with id " + courierId + " does not exist!");
    }
}
