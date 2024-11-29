package org.buildcode.synchservice.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(nullable = false, length = 255)
    private String source; // Source location

    @Column(nullable = false, length = 255)
    private String destination; // Destination location

    @Column(nullable = false)
    private String userId; // User who posted the ride

    @Column(nullable = false)
    private BigInteger seats; // Number of seats

    @Column(nullable = false, unique = true)
    private String vehicleNumber; // Vehicle number

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status; // Ride status

    @Column(nullable = false)
    private BigInteger fare; // Fare for the ride

    @Column(nullable = false, updatable = false)
    private Instant createdAt; // Timestamp for creation

    @Column(nullable = false)
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