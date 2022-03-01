package com.cagdasyilmaz.couriertrackerservice.store.exception;

public class StoreAlreadyFunctionalException extends StoreException {
    public StoreAlreadyFunctionalException(String storeName) {
        super(storeName + " is already functioning!");
    }
}
