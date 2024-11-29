package org.buildcode.rideservice.repository;

import jakarta.transaction.Transactional;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.data.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RideRepository extends JpaRepository<Ride, String> {
    public boolean existsByUserIdAndAndStatus(String userId, RideStatus status);
}