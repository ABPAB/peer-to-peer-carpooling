package org.buildcode.synchservice.repository;

import jakarta.transaction.Transactional;
import org.buildcode.synchservice.data.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RideRepository extends JpaRepository<Ride, String> {

}