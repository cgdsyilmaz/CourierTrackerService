package com.cagdasyilmaz.couriertrackerservice.courier.service;

import com.cagdasyilmaz.couriertrackerservice.courier.entity.Courier;
import com.cagdasyilmaz.couriertrackerservice.courier.exception.CourierAlreadyEmployedException;
import com.cagdasyilmaz.couriertrackerservice.courier.exception.CourierNotFoundException;
import com.cagdasyilmaz.couriertrackerservice.courier.repository.CourierRepository;
import com.cagdasyilmaz.couriertrackerservice.location.controller.model.CourierLocationUpdate;
import com.cagdasyilmaz.couriertrackerservice.location.event.CourierLocationUpdateEvent;
import com.cagdasyilmaz.couriertrackerservice.util.DistanceCalculator;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;

    public CourierServiceImpl(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
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
        if (courierRepository.findCourierByEmailIgnoreCase(email).isPresent())
            throw new CourierAlreadyEmployedException(email);
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

    @EventListener
    @Transactional
    public void handleCourierLocationEvent(final CourierLocationUpdateEvent courierLocationUpdateEvent) {
        UUID courierId = courierLocationUpdateEvent.getCourierId();
        CourierLocationUpdate courierLocationUpdate = courierLocationUpdateEvent.getCourierLocationUpdate();

        Courier courier = courierRepository.findCourierById(courierId)
                .orElseThrow(() -> new CourierNotFoundException(courierId));

        updateTotalDistance(courier, courierLocationUpdate);
        setCourierLocationProperties(courier, courierLocationUpdate);

        courierRepository.save(courier);
    }

    private void updateTotalDistance(Courier courier, CourierLocationUpdate courierLocationUpdate) {
        courier.setTotalDistanceTraveled(courier.getTotalDistanceTraveled() +
                DistanceCalculator.calculateDistance(courier.getLastLatitude(), courier.getLastLongitude(), courierLocationUpdate.getLatitude(), courierLocationUpdate.getLongitude()));
    }

    private void setCourierLocationProperties(Courier courier, CourierLocationUpdate courierLocationUpdate) {
        courier.setLastLocationUpdateTime(LocalDateTime.now());
        courier.setLastLatitude(courierLocationUpdate.getLatitude());
        courier.setLastLongitude(courierLocationUpdate.getLongitude());
    }
}
