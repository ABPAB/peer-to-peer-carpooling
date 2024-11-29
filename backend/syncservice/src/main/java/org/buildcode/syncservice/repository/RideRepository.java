package org.buildcode.syncservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RideRepository extends JpaRepository<org.buildcode.syncservice.data.entity.Ride, String> {

}