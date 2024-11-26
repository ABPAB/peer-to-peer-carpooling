package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RideDetails {

    @Schema(description = "Unique identifier for the ride", example = "abc123")
    private String rideId;

    @Schema(description = "Source location of the ride")
    private String source;

    @Schema(description = "Destination location of the ride")
    private String destination;

    @Schema(description = "Scheduled departure time", example = "2024-11-12T09:30:00")
    private LocalDateTime departureTime;

    @Schema(description = "Seats available on the ride", example = "3")
    private Integer availableSeats;

//    @Schema(description = "Owner details")
//    private Owner owner;

    @Schema(description = "Owner id")
    private String ownerId;

    @Schema(description = "Vehicle details")
    private Vehicle vehicle;
}
