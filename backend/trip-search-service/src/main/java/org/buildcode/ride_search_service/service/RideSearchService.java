// src/main/java/org/buildcode/rideservice/service/RideSearchService.java
package org.buildcode.ride_search_service.service;

import org.buildcode.ride_search_service.api.model.v1_0.RideSearchRequestModel;
import org.buildcode.ride_search_service.api.model.v1_0.RideSearchResultResponseModel;
import org.buildcode.ride_search_service.api.model.v1_0.RideDetails;
import org.buildcode.ride_search_service.data.entity.Ride;
import org.buildcode.ride_search_service.repository.RideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideSearchService {

    @Autowired
    private RideRepository rideRepository;

    private static final int MAX_PAGE_SIZE = 100;
    private static final int MIN_PAGE_SIZE = 1;
    private static final int MIN_PAGE = 0;

    public RideSearchResultResponseModel searchRides(RideSearchRequestModel requestModel, int page, int size) {
        // Validate only required fields
        if (requestModel.getSource() == null || requestModel.getDestination() == null) {
            throw new IllegalArgumentException("Source and destination are required fields");
        }

        // Validate page and size parameters
        if (page < MIN_PAGE) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }
        if (size < MIN_PAGE_SIZE || size > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("Page size must be between " + MIN_PAGE_SIZE + " and " + MAX_PAGE_SIZE);
        }

        Page<Ride> ridesPage;

        // Use different repository queries depending on whether seats is provided
        if (requestModel.getSeats() != null) {
            ridesPage = rideRepository.findRidesWithSeats(
                    requestModel.getSource(),
                    requestModel.getDestination(),
                    requestModel.getSeats(),
                    PageRequest.of(page, size)
            );
        } else {
            ridesPage = rideRepository.findRidesWithoutSeats(
                    requestModel.getSource(),
                    requestModel.getDestination(),
                    PageRequest.of(page, size)
            );
        }

        // Populate response model based on found rides
        if (ridesPage.isEmpty()) {
            return RideSearchResultResponseModel.builder()
                    .status("No rides found")
                    .rides(List.of())
                    .build();
        }

        List<RideDetails> rideDetailsList = ridesPage.stream()
                .map(ride -> RideDetails.builder()
                        .source(ride.getSource())
                        .destination(ride.getDestination())
                        .ownerId(ride.getUserId())
                        .availableSeats(ride.getSeats())
                        .build())
                .collect(Collectors.toList());

        return RideSearchResultResponseModel.builder()
                .status("success")
                .rides(rideDetailsList)
                .page(page)
                .size(size)
                .totalElements(ridesPage.getTotalElements())
                .totalPages(ridesPage.getTotalPages())
                .build();
    }
}
