package org.buildcode.rideservice.api.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.entity.Ride;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Ride Details Resource")

@RestController
@RequestMapping(value = ApiConstants.BOOKING_SERVICE_V1)
public interface RideResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = ApiConstants.MESSAGE_CREATED_FOR_RIDE),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_INVALID_RIDE),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_RIDE_NOT_FOUND),
                    @ApiResponse(responseCode = "409", description = ApiConstants.MESSAGE_RIDE_ALREADY_EXISTS),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR)
            }
    )
    @Operation(method = "POST", summary = "Create Ride")
    @PostMapping("/")
    ResponseEntity<Ride> createRideRequest(
            @RequestBody CreateRideRequestModel createRideRequestModel
    );

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_INVALID_RIDE),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_RIDE_NOT_FOUND)
            }
    )
    @Operation(method = "GET", summary = "Get Ride Details By Id")
    @GetMapping("/{id}")
    ResponseEntity<RideResponseModel> getRideRequest(
            @Parameter(name = "id", description = "ride id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String id
    );

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_RIDE_NOT_FOUND)
            }
    )
    @Operation(method = "DELETE", summary = "Delete Ride By Id")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteRideRequest(
            @Parameter(name = "id", description = "ride id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String id,
            @Parameter(name = "ownerId", description = "owner id")
            @Schema(description = "Owner Id", example = "ownerIDdjc", required = true)
            @PathVariable String ownerId
    );

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "PATCH", summary = "Update Ride data")
    @PatchMapping("/{id}")
    ResponseEntity<Ride> updateRideRequest(
            @Parameter(name = "id", description = "ride id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String id,

            @RequestBody CreateRideRequestModel createRideRequestMode
    );
}