package com.cagdasyilmaz.couriertrackerservice.courier.controller.model.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CourierResponse {
    private UUID id;
    private String name;
    private String email;
}
