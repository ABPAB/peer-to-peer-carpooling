package org.buildcode.rideservice.repository;

import jakarta.transaction.Transactional;
import org.buildcode.rideservice.data.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RideBookingRequestRepository extends JpaRepository<BookingRequest,String> {

}
