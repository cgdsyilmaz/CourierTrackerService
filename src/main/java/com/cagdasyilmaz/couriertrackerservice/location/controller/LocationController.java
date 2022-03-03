package com.cagdasyilmaz.couriertrackerservice.location.controller;

import com.cagdasyilmaz.couriertrackerservice.location.controller.model.CourierLocationUpdate;
import com.cagdasyilmaz.couriertrackerservice.location.event.CourierLocationUpdateEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/location")
public class LocationController {
    private final ApplicationEventPublisher applicationEventPublisher;

    public LocationController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("/{courierId}")
    public ResponseEntity<String> updateCourierLocation(@PathVariable UUID courierId,
                                                        @RequestBody @Validated CourierLocationUpdate courierLocationUpdate) {
        applicationEventPublisher.publishEvent(
                new CourierLocationUpdateEvent(this, courierLocationUpdate, courierId));
        return ResponseEntity.ok().build();
    }
}
