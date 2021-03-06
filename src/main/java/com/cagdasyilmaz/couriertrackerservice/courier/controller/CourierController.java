package com.cagdasyilmaz.couriertrackerservice.courier.controller;

import com.cagdasyilmaz.couriertrackerservice.courier.controller.mapper.CourierMapper;
import com.cagdasyilmaz.couriertrackerservice.courier.controller.model.request.CourierHireRequest;
import com.cagdasyilmaz.couriertrackerservice.courier.controller.model.response.CourierResponse;
import com.cagdasyilmaz.couriertrackerservice.courier.entity.Courier;
import com.cagdasyilmaz.couriertrackerservice.courier.service.CourierService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/courier")
public class CourierController {
    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping()
    public ResponseEntity<List<CourierResponse>> getAllCouriers() {
        List<Courier> couriers = courierService.getAllCouriers();
        List<CourierResponse> courierResponses = couriers.stream()
                .map(CourierMapper::mapCourierToCourierResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(courierResponses);
    }

    @GetMapping("/{courierId}")
    public ResponseEntity<CourierResponse> getCourier(@PathVariable UUID courierId) {
        return ResponseEntity.ok().body(CourierMapper.mapCourierToCourierResponse(courierService.getCourier(courierId)));
    }

    @GetMapping("/total-distance/{courierId}")
    public ResponseEntity<Double> getTotalDistance(@PathVariable UUID courierId) {
            return ResponseEntity.ok().body(courierService.getCourier(courierId).getTotalDistanceTraveled());
    }

    @PostMapping("/hire")
    public ResponseEntity<String> hireCourier(@RequestBody @Validated CourierHireRequest courierHireRequest) {
        UUID courierId = courierService.hireCourier(CourierMapper.mapCourierHireRequestToCourier(courierHireRequest));
        return ResponseEntity.created(URI.create("/v1/courier/" + courierId)).build();
    }

    @DeleteMapping("/{courierId}")
    public ResponseEntity<String> fireCourier(@PathVariable UUID courierId) {
        courierService.fireCourier(courierId);
        return ResponseEntity.ok().build();
    }

}
