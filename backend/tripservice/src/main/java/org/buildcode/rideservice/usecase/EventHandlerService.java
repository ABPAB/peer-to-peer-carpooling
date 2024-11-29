package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.dto.RideNotificationPayload;

public interface EventHandlerService {

    void handleRideCreatedEvent(RideEventPayload payload, String topic);

    void handleRideUpdatedEvent(RideEventPayload payload);

    void handleNotificationEvent(RideNotificationPayload payload);
}
