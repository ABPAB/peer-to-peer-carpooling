package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {

    @Schema(description = "Vehicle type", example = "sedan")
    private String type;

    @Schema(description = "Vehicle model", example = "HondaCity")
    private String model;

    @Schema(description = "License plate of the vehicle", example = "KA01AB1234")
    private String licensePlate;
}
