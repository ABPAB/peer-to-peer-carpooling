package org.buildcode.rideservice.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.buildcode.rideservice.api.constants.BookingRequestStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;

@Entity
@Table(name = "BookingRequest")
@Data
public class BookingRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String bookingRequestId;

    @Column(name = "riderId", nullable = false)
    private String riderId;

    @Column(name = "rideId", nullable = false)
    private String rideId;

    @Column(name = "status", nullable = false)
    private BookingRequestStatus status;

    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;

    @LastModifiedBy
    @Column(name = "updatedAt", nullable = false)
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