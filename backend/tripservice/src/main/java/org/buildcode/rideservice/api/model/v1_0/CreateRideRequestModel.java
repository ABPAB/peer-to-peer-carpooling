package org.buildcode.rideservice.api.model.v1_0;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.buildcode.rideservice.api.constants.RideStatus;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRideRequestModel {

    @Schema(description = "Source location of the ride", example = "Central Park, New York")
    private String source;

    @Schema(description = "Destination location of the ride", example = "Times Square, New York")
    private String destination;

    @Schema(description = "ID of the user who created the ride request", example = "user789xyz")
    private String userId;

    @Schema(description = "Number of seats requested", example = "2")
    private BigInteger seats;

    @Schema(description = "Model of the car for the ride", example = "Toyota Prius")
    private String carModel;

    @Schema(description = "Current status of the ride", example = "CREATED")
    private RideStatus status;

    @Schema(description = "Timestamp when the ride request was created", example = "2023-08-01T10:15:30Z")
    private Instant createdAt;

    @Schema(description = "Timestamp when the ride request was last updated", example = "2023-08-01T12:45:30Z")
    private Instant updatedAt;

    @Schema(description = "fare for the ride created", example= "1000")
    private BigInteger fare;

    @Schema(description = "departureTime for the ride", example= "9:53AM")
    private BigInteger departureTime;

    @Schema(description = "departureDate for the ride", example= "23/10/25")
    private BigInteger departureDate;
}