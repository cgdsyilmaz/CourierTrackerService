package com.cagdasyilmaz.couriertrackerservice.location.controller.model;

import com.cagdasyilmaz.couriertrackerservice.location.util.LocationValidationConstraints;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CourierLocationUpdate {
    @NotNull(message = LocationValidationConstraints.COURIER_LATITUDE_NULL_MESSAGE)
    private Double latitude;

    @NotNull(message = LocationValidationConstraints.COURIER_LONGITUDE_NULL_MESSAGE)
    private Double longitude;

    @NotNull(message = LocationValidationConstraints.COURIER_UPDATE_TIME_NULL_MESSAGE)
    private LocalDateTime updateTime;
}
