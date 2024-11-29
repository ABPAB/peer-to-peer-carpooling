package org.buildcode.rideservice.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import org.buildcode.rideservice.api.constants.BookingRequestStatus;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequestResponseModel {

    @Schema(description = "booking request id", example = "kjhabsd78w3jba78s")
    String id;

    @Schema(description = "rideId", example = "8u3wk976")
    String rideId;

    @Column(name = "riderId", nullable = false)
    private String riderId;

    @Schema(description = "booking request status", example = "PENDING")
    BookingRequestStatus status;

    @Schema(description = "Timestamp when the ride request was created", example = "2023-08-01T10:15:30Z")
    private Instant createdAt;

    @Schema(description = "Timestamp when the ride request was last updated", example = "2023-08-01T12:45:30Z")
    private Instant updatedAt;
}