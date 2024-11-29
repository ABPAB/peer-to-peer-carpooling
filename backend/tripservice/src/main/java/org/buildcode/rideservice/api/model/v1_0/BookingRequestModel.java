package org.buildcode.rideservice.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.buildcode.rideservice.api.constants.BookingRequestStatus;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequestModel {

    @Schema(description = "rideId", example = "8u3wk976")
    String rideId;

    @Schema(description = "RiderId who requested for the booking", example = "98nasyu3w423")
    private String riderId;
}