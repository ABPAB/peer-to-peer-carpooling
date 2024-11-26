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

    public RideSearchResultResponseModel searchRides(RideSearchRequestModel requestModel, int page, int size) {
        // Validate only required fields
        if (requestModel.getSource() == null || requestModel.getDestination() == null) {
            throw new IllegalArgumentException("Source and destination are required fields");
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
