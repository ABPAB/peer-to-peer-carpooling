package org.buildcode.synchservice.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.buildcode.synchservice.api.constants.RideStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "Rides")
@Data
public class Ride {

    @Id
    @Column(length = 36, nullable = false, unique = true)
    private String id;

    @Column(name = "source", nullable = true)
    private String source;

    @Column(name = "destination", nullable = true)
    private String destination;

    @Column(name = "ownerId", nullable = true)
    private String ownerId;

    @Column(name = "seats", nullable = true)
    private BigInteger seats;

    @Column(name = "vehicleNumber", nullable = true)
    private String vehicleNumber;

    @Column(name = "fare", nullable = true)
    private BigInteger fare;

    @Column(name = "deviceToken", nullable = true)
    private String deviceToken;

    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private RideStatus status;

    @Column(name = "departureTime", nullable = true)
    private String departureTime;

    @Column(name = "departureDate", nullable = true)
    private String departureDate;

    @CreatedDate
    @Column(name = "createdAt", nullable = true)
    private Instant createdAt;

    @LastModifiedBy
    @Column(name = "updatedAt", nullable = true)
    private Instant updatedAt;

    @PrePersist
    protected  void prePersist(){
        if(this.createdAt == null){
            createdAt = Instant.now();
        }
        if(this.updatedAt == null){
            updatedAt = Instant.now();
        }
    }

    @PreUpdate
    protected void preUpdate(){
        this.updatedAt = Instant.now();
    }
}