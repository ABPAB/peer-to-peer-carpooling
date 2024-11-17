package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RideSearchRequestModel {

    @NotNull(message = "Source location is required")
    @Schema(description = "Source location for the ride", example = "location")
    private String source;

    @NotNull(message = "Destination location is required")
    @Schema(description = "Destination location for the ride", example = "location")
    private String destination;

    @Schema(description = "Seats required", example = "4")
    private Integer seats;
}
