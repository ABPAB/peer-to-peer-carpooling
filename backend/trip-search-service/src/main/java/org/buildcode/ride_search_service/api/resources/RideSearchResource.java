// src/main/java/org/buildcode/ride_search_service/api/resources/RideSearchResource.java
package org.buildcode.ride_search_service.api.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.buildcode.ride_search_service.api.constants.ApiConstants;
import org.buildcode.ride_search_service.api.model.v1_0.RideSearchRequestModel;
import org.buildcode.ride_search_service.api.model.v1_0.RideSearchResultResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ride Search Resource")
@RequestMapping(value = ApiConstants.RIDE_SERVICE_V1 + ApiConstants.SEARCH)
public interface RideSearchResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Search rides")
    @PostMapping
    ResponseEntity<RideSearchResultResponseModel> getRides(
            @RequestBody RideSearchRequestModel searchRequestModel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    );
}
