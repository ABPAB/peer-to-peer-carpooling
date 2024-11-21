package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.api.model.v1_0.RideDeviceDetails;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;

public interface RideDeviceService {

    RideResponseModel updateDeviceDetails(String id, RideDeviceDetails rideDeviceDetails);
}