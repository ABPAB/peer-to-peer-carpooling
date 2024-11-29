package org.buildcode.synchservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.synchservice.api.model.v1_0.RideUpdateRequestModel;
import org.buildcode.synchservice.api.resources.SyncResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class SyncController implements SyncResource {

    @Override
    public ResponseEntity<String> updateRideRequest(String id, RideUpdateRequestModel rideUpdateRequestModel) {
        return null;
    }
}