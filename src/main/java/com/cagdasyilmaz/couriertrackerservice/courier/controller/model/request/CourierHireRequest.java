package com.cagdasyilmaz.couriertrackerservice.courier.controller.model.request;

import com.cagdasyilmaz.couriertrackerservice.location.util.LocationValidationConstraints;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CourierHireRequest {
    @NotBlank(message = LocationValidationConstraints.COURIER_NAME_BLANK_MESSAGE)
    private String name;

    @Email(message = LocationValidationConstraints.COURIER_EMAIL_NOT_VALID_MESSAGE)
    private String email;
}
