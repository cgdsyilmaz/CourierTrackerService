package com.cagdasyilmaz.couriertrackerservice.store.service;

import com.cagdasyilmaz.couriertrackerservice.location.event.CourierLocationUpdateEvent;
import com.cagdasyilmaz.couriertrackerservice.store.entity.Store;
import com.cagdasyilmaz.couriertrackerservice.store.exception.StoreAlreadyFunctionalException;
import com.cagdasyilmaz.couriertrackerservice.store.exception.StoreNotFoundException;
import com.cagdasyilmaz.couriertrackerservice.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStore(UUID storeId) {
        return storeRepository.findStoreById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId));
    }

    @Override
    @Transactional
    public UUID openNewStore(Store store) {
        validateStore(store);
        setStoreProperties(store);

        storeRepository.save(store);
        return store.getId();
    }

    private void validateStore(Store store) {
        String storeName = store.getName();
        if (storeRepository.findStoreByName(storeName).isPresent()) {
            throw new StoreAlreadyFunctionalException(storeName);
        }
    }

    private void setStoreProperties(Store store) {
        store.setId(UUID.randomUUID());
        store.setDateOpened(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void closeStore(UUID storeId) {
        storeRepository.deleteStoreById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId));
    }

    @EventListener
    @Transactional
    public void handleCourierLocationEvent(final CourierLocationUpdateEvent courierLocationUpdateEvent) {
        
    }
}
