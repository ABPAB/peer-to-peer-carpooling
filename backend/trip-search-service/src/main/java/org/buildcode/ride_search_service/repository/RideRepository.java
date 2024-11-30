package org.buildcode.ride_search_service.repository;

import org.buildcode.ride_search_service.data.entity.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RideRepository extends CrudRepository<Ride, String> {

    // Query method to find rides based on source, destination, departure date, and status
    @Query("SELECT r FROM Ride r WHERE r.source = :source AND r.destination = :destination AND r.departureDate = :departureDate AND r.status = :status")
    Page<Ride> findRidesBySourceDestinationDepartureDateAndStatus(String source, String destination, String departureDate, String status, Pageable pageable);
}
