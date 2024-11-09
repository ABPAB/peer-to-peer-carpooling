package org.buildcode.rideservice.data.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;

@Entity
@Table(name = "Rides")
@Data
public class Ride {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "source", nullable = false)
    String source;

    @Column(name = "destination", nullable = false)
    String destination;

    @Column(name = "userId", nullable = false)
    String userId;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "carModel", nullable = false)
    String carModel;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RideStatus status;

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