package com.cagdasyilmaz.couriertrackerservice.store.controller;

import com.cagdasyilmaz.couriertrackerservice.store.controller.mapper.StoreMapper;
import com.cagdasyilmaz.couriertrackerservice.store.controller.model.request.StoreOpenRequest;
import com.cagdasyilmaz.couriertrackerservice.store.controller.model.response.StoreResponse;
import com.cagdasyilmaz.couriertrackerservice.store.entity.Store;
import com.cagdasyilmaz.couriertrackerservice.store.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping()
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        List<StoreResponse> storeResponses = stores.stream()
                .map(StoreMapper::mapStoreToStoreResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(storeResponses);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse> getStore(@PathVariable UUID storeId) {
        return ResponseEntity.ok().body(StoreMapper.mapStoreToStoreResponse(storeService.getStore(storeId)));
    }

    @PostMapping("/open")
    public ResponseEntity<String> openNewStore(@RequestBody @Validated StoreOpenRequest storeOpenRequest) {
        UUID storeId = storeService.openNewStore(StoreMapper.mapStoreOpenRequestToStore(storeOpenRequest));
        return ResponseEntity.created(URI.create("/v1/store" + storeId)).build();
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> closeStore(@PathVariable UUID storeId) {
        storeService.closeStore(storeId);
        return ResponseEntity.ok().build();
    }
}
