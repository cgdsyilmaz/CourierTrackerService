package com.cagdasyilmaz.couriertrackerservice.courier.service;

import com.cagdasyilmaz.couriertrackerservice.courier.controller.model.request.CourierLocationUpdate;
import com.cagdasyilmaz.couriertrackerservice.courier.entity.Courier;
import com.cagdasyilmaz.couriertrackerservice.courier.repository.CourierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final StoreService storeService;

    public CourierServiceImpl(CourierRepository courierRepository, StoreService storeService) {
        this.courierRepository = courierRepository;
        this.storeService = storeService;
    }

    @Override
    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    @Override
    public Courier getCourier(UUID courierId) {
        return courierRepository.findCourierById(courierId).orElseThrow(() -> new CourierNotFoundException(courierId));
    }

    @Override
    @Transactional
    public UUID hireCourier(Courier courier) {
        validateCourier(courier);
        setCourierProperties(courier);

        courierRepository.save(courier);
        return courier.getId();
    }

    private void validateCourier(Courier courier) {
        String email = courier.getEmail();
        if (courierRepository.findCourierByEmail(email).isPresent())
            throw new CourierIsAlreadyEmployedException(email);
    }

    private void setCourierProperties(Courier courier) {
        courier.setId(UUID.randomUUID());
        courier.setTotalDistanceTraveled(0);
    }

    @Override
    @Transactional
    public void fireCourier(UUID courierId) {
        courierRepository.deleteCourierById(courierId).orElseThrow(() -> new CourierNotFoundException(courierId));
    }

    @Override
    @Transactional
    public void updateCourierLocation(UUID courierId, CourierLocationUpdate courierLocationUpdate) {
        Courier courier = courierRepository.findCourierById(courierId).orElseThrow(() -> new CourierNotFoundException(courierId));

        setCourierLocationProperties(courierLocationUpdate, courier);
        computeTotalDistance(courier);
        checkAndLogStoreDistances(courier, storeService.getStores());
    }

    private void setCourierLocationProperties(CourierLocationUpdate courierLocationUpdate, Courier courier) {
        courier.setLastLocationUpdateDate(LocalDateTime.now());
        courier.setLastLatitude(courierLocationUpdate.getLatitude());
        courier.setLastLongitude(courierLocationUpdate.getLongitude());
    }
}
