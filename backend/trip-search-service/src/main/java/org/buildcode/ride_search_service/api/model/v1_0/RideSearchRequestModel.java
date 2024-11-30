package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RideSearchRequestModel {

    @Schema(description = "Source location for the ride", example = "location", required = true)
    private String source;

    @Schema(description = "Destination location for the ride", example = "location", required = true)
    private String destination;

    @Pattern(
            regexp = "^\\d{2}/\\d{2}/\\d{4}$",
            message = "Departure date must be in the format dd/MM/yyyy"
    )
    @Schema(
            description = "Departure date in the format dd/MM/yy (optional)",
            example = "15/12/2024"
    )
    private String departureDate;
}
