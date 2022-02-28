package com.cagdasyilmaz.couriertrackerservice.courier.controller.model.request;

import com.cagdasyilmaz.couriertrackerservice.courier.util.CourierValidationConstraints;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CourierHireRequest {
    @NotBlank(message = CourierValidationConstraints.COURIER_NAME_BLANK_MESSAGE)
    private String name;

    @Email(message = CourierValidationConstraints.COURIER_EMAIL_NOT_VALID_MESSAGE)
    private String email;
}
