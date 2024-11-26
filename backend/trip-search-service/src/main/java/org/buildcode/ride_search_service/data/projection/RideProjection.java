// src/main/java/org/buildcode/rideservice/data/projection/RideProjection.java
package org.buildcode.ride_search_service.data.projection;

public interface RideProjection {
    String getSource();
    String getDestination();
    String getUserId();
    Integer getSeats();
}
