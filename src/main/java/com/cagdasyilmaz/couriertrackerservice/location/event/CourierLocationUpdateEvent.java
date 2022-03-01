package com.cagdasyilmaz.couriertrackerservice.location.event;

import com.cagdasyilmaz.couriertrackerservice.location.controller.model.CourierLocationUpdate;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class CourierLocationUpdateEvent extends ApplicationEvent {
    private final CourierLocationUpdate courierLocationUpdate;
    private final UUID courierId;

    public CourierLocationUpdateEvent(final Object eventSource,
                                      final CourierLocationUpdate courierLocationUpdate,
                                      final UUID courierId) {
        super(eventSource);
        this.courierLocationUpdate = courierLocationUpdate;
        this.courierId = courierId;
    }
}
