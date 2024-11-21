package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RideSearchResultResponseModel {

    @Schema(description = "Status of the search request", example = "success")
    private String status;

    @Schema(description = "List of rides that match the search criteria")
    private List<RideDetails> rides;

    @Schema(description = "Current page number", example = "0")
    private Integer page;

    @Schema(description = "Page size", example = "10")
    private Integer size;

    @Schema(description = "Total number of elements available", example = "50")
    private Long totalElements;

    @Schema(description = "Total number of pages available", example = "5")
    private Integer totalPages;
}
