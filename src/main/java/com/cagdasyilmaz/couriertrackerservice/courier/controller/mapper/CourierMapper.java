package com.cagdasyilmaz.couriertrackerservice.courier.controller.mapper;

import com.cagdasyilmaz.couriertrackerservice.courier.controller.model.request.CourierHireRequest;
import com.cagdasyilmaz.couriertrackerservice.courier.controller.model.response.CourierResponse;
import com.cagdasyilmaz.couriertrackerservice.courier.entity.Courier;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {
    private static ModelMapper modelMapper;

    public CourierMapper(ModelMapper modelMapper) {
        CourierMapper.modelMapper = modelMapper;
    }

    public static CourierResponse mapCourierToCourierResponse(Courier courier) {
        return modelMapper.map(courier, CourierResponse.class);
    }

    public static Courier mapCourierHireRequestToCourier(CourierHireRequest courierHireRequest) {
        return modelMapper.map(courierHireRequest, Courier.class);
    }
}
