package com.cagdasyilmaz.couriertrackerservice.store.controller.mapper;

import com.cagdasyilmaz.couriertrackerservice.store.controller.model.request.StoreOpenRequest;
import com.cagdasyilmaz.couriertrackerservice.store.controller.model.response.StoreResponse;
import com.cagdasyilmaz.couriertrackerservice.store.entity.Store;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    private static ModelMapper modelMapper;

    public StoreMapper(ModelMapper modelMapper) {
        StoreMapper.modelMapper = modelMapper;
    }

    public static StoreResponse mapStoreToStoreResponse(Store store) {
        return modelMapper.map(store, StoreResponse.class);
    }

    public static Store mapStoreOpenRequestToStore(StoreOpenRequest storeOpenRequest) {
        return modelMapper.map(storeOpenRequest, Store.class);
    }
}
