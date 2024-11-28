package org.buildcode.rideservice.api.resources;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Ride Request Resource")

@RestController
@RequestMapping(value = ApiConstants.BOOKING_SERVICE_V1 + ApiConstants.BOOKING_REQUEST)
public interface RideBookingRequestResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Create booking request")
    @PostMapping
    ResponseEntity<BookingRequestResponseModel> createBookingRequest(
            @RequestBody BookingRequestModel requestModel
    );


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "GET", summary = "Get booking request details by id")
    @GetMapping("/{bookingRequestId}")
    ResponseEntity<BookingRequestResponseModel> getBookingRequest(
            @Parameter(name = "bookingRequestId", description = "booking request id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String bookingRequestId
    );

    @Operation(method = "POST", summary = "Accept Ride Booking Request")
    @PostMapping("/{bookingRequestId}/accept")
    ResponseEntity<Boolean> acceptRideBookingRequest(
            @Parameter(name = "bookingRequestId", description = "bookingRequestId")
            @Schema(description = "Booking Request ID", example = "8732njsf87yh", required = true)
            @PathVariable String bookingRequestId,

            @Parameter(name = "ownerId", description = "ownerId")
            @Schema(description = "Owner Id", example = "sdfkfadf", required = true)
            @PathVariable String ownerId
    );

    @Operation(method = "POST", summary = "Reject Ride Request")
    @PostMapping("/{bookingRequestId}/reject")
    ResponseEntity<Boolean> rejectRideBookingRequest(
            @Parameter(name = "bookingRequestId", description = "bookingRequestId")
            @Schema(description = "Booking Request ID", example = "8732njsf87yh", required = true)
            @PathVariable String bookingRequestId
    );
}