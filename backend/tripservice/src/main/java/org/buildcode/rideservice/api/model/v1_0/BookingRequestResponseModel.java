package org.buildcode.rideservice.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.buildcode.rideservice.api.constants.BookingRequestStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequestResponseModel {

    @Schema(description = "booking request id", example = "kjhabsd78w3jba78s")
    String id;

    @Schema(description = "rideId", example = "8u3wk976")
    String rideId;

    @Schema(description = "booking request status", example = "PENDING")
    BookingRequestStatus status;

    @Schema(description = "created at", example = "date")
    private String createdAt;

    @Schema(description = "updated at", example = "date")
    private String updatedAt;
}