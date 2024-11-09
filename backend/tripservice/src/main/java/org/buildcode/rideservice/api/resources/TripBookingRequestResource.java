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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Booking Request Resource")

@RestController
@RequestMapping(value = ApiConstants.BOOKING_SERVICE_V1 + ApiConstants.BOOKING_REQUEST)
public interface TripBookingRequestResource {

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
}