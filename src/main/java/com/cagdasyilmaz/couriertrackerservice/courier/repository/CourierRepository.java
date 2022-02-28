package com.cagdasyilmaz.couriertrackerservice.courier.repository;

import com.cagdasyilmaz.couriertrackerservice.courier.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID> {
    Optional<Courier> findCourierById(UUID id);
    Optional<Courier> findCourierByEmail(String email);
    Optional<Courier> deleteCourierById(UUID id);
}
