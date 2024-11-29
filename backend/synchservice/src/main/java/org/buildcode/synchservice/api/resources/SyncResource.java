package org.buildcode.synchservice.api.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.buildcode.synchservice.api.constants.ApiConstants;
import org.buildcode.synchservice.api.model.v1_0.RideUpdateRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Ride Details Resource")

@RestController
@RequestMapping(value = ApiConstants.SYNC_SERVICE_V1)
public interface SyncResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "POST", summary = "Update Ride data")
    @PostMapping("/{id}")
    ResponseEntity<String> updateRideRequest(
            @Parameter(name = "id", description = "ride id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String id,

            @RequestBody RideUpdateRequestModel rideUpdateRequestModel
    );
}