package org.buildcode.rideservice.api.resources;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Booking Details Resource")

@RestController
@RequestMapping(value = ApiConstants.BOOKING_SERVICE_V1 + ApiConstants.BOOKING_REQUEST)
public interface TripResource {




}