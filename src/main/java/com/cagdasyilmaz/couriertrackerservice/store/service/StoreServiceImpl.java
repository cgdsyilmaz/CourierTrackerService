package com.cagdasyilmaz.couriertrackerservice.store.service;

import com.cagdasyilmaz.couriertrackerservice.location.controller.model.CourierLocationUpdate;
import com.cagdasyilmaz.couriertrackerservice.location.event.CourierLocationUpdateEvent;
import com.cagdasyilmaz.couriertrackerservice.store.entity.Store;
import com.cagdasyilmaz.couriertrackerservice.store.exception.StoreAlreadyFunctionalException;
import com.cagdasyilmaz.couriertrackerservice.store.exception.StoreNotFoundException;
import com.cagdasyilmaz.couriertrackerservice.store.repository.StoreRepository;
import com.cagdasyilmaz.couriertrackerservice.store.util.StoreEntranceConstants;
import com.cagdasyilmaz.couriertrackerservice.util.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        CourierLocationUpdate courierLocationUpdate = courierLocationUpdateEvent.getCourierLocationUpdate();
        List<Store> stores = storeRepository.findAll();

        List<Store> storesEntered = findStoresAndLog(courierLocationUpdateEvent.getCourierId(),
                courierLocationUpdate, stores);

        storesEntered.forEach(store -> store.setLastEntryTime(courierLocationUpdate.getUpdateTime()));
        storeRepository.saveAll(storesEntered);
    }

    private List<Store> findStoresAndLog(UUID courierId, CourierLocationUpdate courierLocationUpdate, List<Store> stores) {
        return stores.stream()
                .filter(store -> eligibleForEntry(store, courierLocationUpdate))
                .peek(store -> log.info("Courier with id {} entered store {}", courierId, store.getName()))
                .collect(Collectors.toList());
    }

    private boolean eligibleForEntry(Store store, CourierLocationUpdate courierLocationUpdate) {
        return hasSufficientTimePassed(store, courierLocationUpdate) && hasEnteredRadius(store, courierLocationUpdate);
    }

    private boolean hasEnteredRadius(Store store, CourierLocationUpdate courierLocationUpdate) {
        double distance = DistanceCalculator.calculateDistance(courierLocationUpdate.getLatitude(),
                courierLocationUpdate.getLongitude(), store.getLatitude(), store.getLongitude());

        return distance <= StoreEntranceConstants.ENTRY_DISTANCE;
    }

    private boolean hasSufficientTimePassed(Store store, CourierLocationUpdate courierLocationUpdate) {
        LocalDateTime lastEntryTime = store.getLastEntryTime();
        if (lastEntryTime == null)
            return true;
        return ChronoUnit.SECONDS.between(lastEntryTime, courierLocationUpdate.getUpdateTime())
                > StoreEntranceConstants.LOG_TIME_OUT;
    }
}
