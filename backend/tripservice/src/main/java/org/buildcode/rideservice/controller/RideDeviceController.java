package org.buildcode.rideservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.model.v1_0.RideDeviceDetails;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.api.resources.RideDeviceResource;
import org.buildcode.rideservice.usecase.RideDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class RideDeviceController implements RideDeviceResource {

    @Autowired
    private RideDeviceService rideDeviceService;


    @Override
    public ResponseEntity<RideResponseModel> updateRideRequest(String id, RideDeviceDetails rideDeviceDetails) {
        log.info("Got the request for ride device update: {} {}", id, rideDeviceDetails);
        RideResponseModel rideResponseModel = rideDeviceService.updateDeviceDetails(id, rideDeviceDetails);
        log.info("Ride device updated successfully!: {}", rideResponseModel);
        return new ResponseEntity<>(rideResponseModel, HttpStatus.OK);
    }
}