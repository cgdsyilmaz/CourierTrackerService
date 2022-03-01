package com.cagdasyilmaz.couriertrackerservice.store.service;

import com.cagdasyilmaz.couriertrackerservice.store.entity.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {
    List<Store> getAllStores();
    Store getStore(UUID storeId);
    void closeStore(UUID storeId);
    UUID openNewStore(Store store);
}
