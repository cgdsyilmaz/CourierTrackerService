package com.cagdasyilmaz.couriertrackerservice.courier.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "couriers")
public class Courier {
    @Id
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private LocalDateTime lastLocationUpdateDate;
    private double lastLatitude;
    private double lastLongitude;
    private double totalDistanceTraveled;

    private String lastLoggedStore;
    private LocalDateTime lastLoggedDate;
}
