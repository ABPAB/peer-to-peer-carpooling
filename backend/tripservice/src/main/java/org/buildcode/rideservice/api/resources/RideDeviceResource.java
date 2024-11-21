package org.buildcode.rideservice.api.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.buildcode.rideservice.api.model.v1_0.RideDetailsModel;
import org.buildcode.rideservice.api.model.v1_0.RideDeviceDetails;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.entity.Ride;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ride Details Resource")

@RestController
@RequestMapping(value = ApiConstants.BOOKING_SERVICE_V1 + ApiConstants.RIDE_DEVICE)
public interface RideDeviceResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "PATCH", summary = "Update Ride Device data")
    @PatchMapping("/{id}")
    ResponseEntity<RideResponseModel> updateRideRequest(
            @Parameter(name = "id", description = "ride id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String id,

            @RequestBody RideDeviceDetails rideDeviceDetails
    );
}