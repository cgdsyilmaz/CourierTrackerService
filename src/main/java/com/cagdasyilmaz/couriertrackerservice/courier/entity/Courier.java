package com.cagdasyilmaz.couriertrackerservice.courier.entity;

import com.cagdasyilmaz.couriertrackerservice.common.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "couriers")

public class Courier {
    @Id
    private UUID courierId;

    private LocalDateTime lastLocationUpdateDate;
    private Location lastLocation;
    private double totalDistanceTravelled;
}
