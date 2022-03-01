package com.cagdasyilmaz.couriertrackerservice.store.controller.model.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StoreResponse {
    private UUID id;
    private String name;
    private LocalDateTime dateOpened;
    private double latitude;
    private double longitude;
}
