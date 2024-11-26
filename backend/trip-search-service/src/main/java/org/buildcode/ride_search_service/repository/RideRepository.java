// src/main/java/org/buildcode/ride_search_service/repository/RideRepository.java
package org.buildcode.ride_search_service.repository;

import org.buildcode.ride_search_service.data.entity.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RideRepository extends CrudRepository<Ride, String> {

    // Query method when seats criteria is provided
    @Query("SELECT r FROM Ride r WHERE r.source = :source AND r.destination = :destination AND r.seats >= :seats")
    Page<Ride> findRidesWithSeats(String source, String destination, Integer seats, Pageable pageable);

    // Query method when seats criteria is not provided, defaults to rides with at least 1 available seat
    @Query("SELECT r FROM Ride r WHERE r.source = :source AND r.destination = :destination AND r.seats >= 1")
    Page<Ride> findRidesWithoutSeats(String source, String destination, Pageable pageable);
}
