package com.cagdasyilmaz.couriertrackerservice.courier.controller.model.request;

import com.cagdasyilmaz.couriertrackerservice.courier.util.CourierValidationConstraints;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CourierLocationUpdate {
    @NotNull(message = CourierValidationConstraints.COURIER_LATITUDE_NULL_MESSAGE)
    private Double latitude;

    @NotNull(message = CourierValidationConstraints.COURIER_LONGITUDE_NULL_MESSAGE)
    private Double longitude;

    @NotNull(message = CourierValidationConstraints.COURIER_UPDATE_TIME_NULL_MESSAGE)
    private LocalDateTime updateTime;
}
