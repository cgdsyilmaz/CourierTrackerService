package com.cagdasyilmaz.couriertrackerservice.store.exception;

import java.util.UUID;

public class StoreNotFoundException extends StoreException {
    public StoreNotFoundException(UUID storeId) {
        super("Store with id " + storeId + " does not exist!");
    }
}
