package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.buildcode.ride_search_service.api.constants.BookingRequestStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RideDetailsResponseModel {

    @Schema(description = "ride Id", example = "kjhabsd78w3jba78s")
    String id;

    @Schema(description = "booking request status", example = "PENDING")
    BookingRequestStatus status;

    @Schema(description = "created at", example = "date")
    private String createdAt;

    @Schema(description = "updated at", example = "date")
    private String updatedAt;
}