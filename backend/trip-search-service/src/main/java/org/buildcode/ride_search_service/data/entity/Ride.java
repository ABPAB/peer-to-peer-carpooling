package org.buildcode.ride_search_service.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.buildcode.ride_search_service.api.constants.RideStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "SearchRides")
@Data
public class Ride {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "ownerId", nullable = false)
    private String ownerId;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "vehicleNumber", nullable = false)
    private String vehicleNumber;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "departureTime", nullable = false)
    private String departureTime;

    @Column(name = "departureDate", nullable = false)
    private String departureDate;

    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    private String createdAt;

    @LastModifiedBy
    @Column(name = "updatedAt", nullable = false)
    private String updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) {
            createdAt = java.time.LocalDateTime.now().toString(); // Set current timestamp as a string
        }
        if (this.updatedAt == null) {
            updatedAt = java.time.LocalDateTime.now().toString(); // Set current timestamp as a string
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = java.time.LocalDateTime.now().toString(); // Update timestamp as a string
    }
}
