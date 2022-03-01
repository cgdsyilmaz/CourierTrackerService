package com.cagdasyilmaz.couriertrackerservice.store.controller.model.request;

import com.cagdasyilmaz.couriertrackerservice.store.util.StoreValidationConstraints;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StoreOpenRequest {
    @NotBlank(message = StoreValidationConstraints.STORE_NAME_BLANK_MESSAGE)
    private String name;

    @NotNull(message = StoreValidationConstraints.STORE_LATITUDE_NULL_MESSAGE)
    private Double latitude;

    @NotNull(message = StoreValidationConstraints.STORE_LONGITUDE_NULL_MESSAGE)
    private Double longitude;
}
