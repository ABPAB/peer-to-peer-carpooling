package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.dto.RideNotificationPayload;
import org.buildcode.rideservice.data.entity.Ride;

public interface EventHandlerService {

    void handleRideCreatedEvent(RideEventPayload payload, String topic);

    void handleRideCreatedEvent(Ride rideDetails, String topic);

    void handleRideUpdatedEvent(RideEventPayload payload);

    void handleNotificationEvent(RideNotificationPayload payload, String topic);

    void handleNotificationEvent(Ride rideDetails, String topic);
}