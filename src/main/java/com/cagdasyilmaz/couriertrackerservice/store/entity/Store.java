package com.cagdasyilmaz.couriertrackerservice.store.entity;

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
@Table(name = "store")
public class Store {
    @Id
    private UUID id;

    @Column(unique = true)
    private String name;

    private LocalDateTime dateOpened;
    private double latitude;
    private double longitude;

    private LocalDateTime lastEntryTime;
}
