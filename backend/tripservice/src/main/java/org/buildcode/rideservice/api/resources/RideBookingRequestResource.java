package org.buildcode.rideservice.api.resources;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ride Request Resource")

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = ApiConstants.BOOKING_SERVICE_V1 + ApiConstants.BOOKING_REQUEST)
public interface RideBookingRequestResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = ApiConstants.MESSAGE_SUCCESS_FOR_BOOKING_REQUEST),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "403", description = ApiConstants.ACCESS_DENIED_FOR_OWNER_ACCESS_TO_BOOKING_REQUEST),
                    @ApiResponse(responseCode = "404", description = ApiConstants.RIDE_REQUESTED_NOT_FOUND),
                    @ApiResponse(responseCode = "409", description = ApiConstants.MESSAGE_BOOKING_REQUEST_ALREADY_EXISTS),
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
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_BOOKING_REQUEST_RETRIEVED_SUCCESSFULLY),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_INVALID_BOOKING_REQUEST_ID),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_BOOKING_REQUEST_NOT_FOUND),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR)
            }
    )
    @Operation(method = "GET", summary = "Get booking request details by id")
    @GetMapping("/{bookingRequestId}")
    ResponseEntity<BookingRequestResponseModel> getBookingRequest(
            @Parameter(name = "bookingRequestId", description = "booking request id")
            @Schema(description = "Reference", example = "8732njsf87yh", required = true)
            @PathVariable String bookingRequestId
    );

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_BOOKING_ACCEPTED),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_BOOKING_REQUEST_TO_ACCEPT_NOT_FOUND),
                    @ApiResponse(responseCode = "403", description = ApiConstants.MESSAGE_ACCESS_DENIED_FOR_RIDER_TO_ACCEPT_BOOKING_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "409", description = ApiConstants.MESSAGE_BOOKING_CONFLICT),
                    @ApiResponse(responseCode = "410", description = ApiConstants.MESSAGE_BOOKING_REQUEST_GONE),
            }
    )
    @Operation(method = "POST", summary = "Accept Ride Booking Request")
    @PostMapping("/{bookingRequestId}/accept/{ownerId}")
    ResponseEntity<Boolean> acceptRideBookingRequest(
            @Parameter(name = "bookingRequestId", description = "bookingRequestId")
            @Schema(description = "Booking Request ID", example = "8732njsf87yh", required = true)
            @PathVariable String bookingRequestId,

            @Parameter(name = "ownerId", description = "ownerId")
            @Schema(description = "Owner Id", example = "sdfkfadf", required = true)
            @PathVariable String ownerId
    );

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_BOOKING_REQUEST_REJECTED),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_BOOKING_REQUEST_TO_REJECT_NOT_FOUND),
                    @ApiResponse(responseCode = "403", description = ApiConstants.MESSAGE_ACCESS_DENIED_FOR_RIDER_TO_REJECT_BOOKING_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "410", description = ApiConstants.MESSAGE_BOOKING_REQUEST_GONE),
            }
    )
    @Operation(method = "POST", summary = "Reject Ride Request")
    @PostMapping("/{bookingRequestId}/reject")
    ResponseEntity<Boolean> rejectRideBookingRequest(
            @Parameter(name = "bookingRequestId", description = "bookingRequestId")
            @Schema(description = "Booking Request ID", example = "8732njsf87yh", required = true)
            @PathVariable String bookingRequestId
    );
}