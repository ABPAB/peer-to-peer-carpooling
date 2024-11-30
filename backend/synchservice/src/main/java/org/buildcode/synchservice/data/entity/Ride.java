package org.buildcode.synchservice.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.buildcode.synchservice.api.constants.RideStatus;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @Column(length = 36, nullable = false, unique = true)
    private String id;

    @Column(nullable = true, length = 255)
    private String source; // Source location

    @Column(nullable = true, length = 255)
    private String destination; // Destination location

    @Column(nullable = true)
    private String userId; // User who posted the ride

    @Column(nullable = true)
    private BigInteger seats; // Number of seats

    @Column(nullable = true, unique = true)
    private String vehicleNumber; // Vehicle number

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private RideStatus status; // Ride status

    @Column(nullable = true)
    private BigInteger fare; // Fare for the ride

    @Column(nullable = true, updatable = false)
    private Instant createdAt; // Timestamp for creation

    @Column(nullable = true)
    private Instant updatedAt; // Timestamp for last update

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}