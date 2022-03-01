package com.cagdasyilmaz.couriertrackerservice.store.repository;

import com.cagdasyilmaz.couriertrackerservice.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
    Optional<Store> findStoreById(UUID storeId);
    Optional<Store> findStoreByName(String storeName);
    Optional<Store> deleteStoreById(UUID storeId);
}
