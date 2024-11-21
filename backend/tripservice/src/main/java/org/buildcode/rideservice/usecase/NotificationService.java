package org.buildcode.rideservice.usecase;

public interface NotificationService {

    void sendNotificationToRider(String userId, String rideAccepted, String s);
}