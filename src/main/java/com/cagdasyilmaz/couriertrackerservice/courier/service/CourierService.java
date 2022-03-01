package com.cagdasyilmaz.couriertrackerservice.courier.service;

import com.cagdasyilmaz.couriertrackerservice.courier.entity.Courier;

import java.util.List;
import java.util.UUID;

public interface CourierService {
    List<Courier> getAllCouriers();
    Courier getCourier(UUID courierId);
    UUID hireCourier(Courier courier);
    void fireCourier(UUID courierId);
}
