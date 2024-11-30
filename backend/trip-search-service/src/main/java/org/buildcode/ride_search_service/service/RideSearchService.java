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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideSearchService {

    @Autowired
    private RideRepository rideRepository;

    public RideSearchResultResponseModel searchRides(RideSearchRequestModel requestModel, int page, int size) {
        // Validate required fields (source and destination are mandatory)
        if (requestModel.getSource() == null || requestModel.getDestination() == null) {
            throw new IllegalArgumentException("Source and destination are required fields");
        }

        // Determine the date to search for
        String searchDate = requestModel.getDepartureDate(); // Use provided date if available
        if (searchDate == null) {
            // If no date is provided, default to the current date
            LocalDate currentDate = LocalDate.now();
            searchDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        // Fetch rides with status 'ACTIVE' based on the determined date, source, and destination
        Page<Ride> ridesPage = rideRepository.findRidesBySourceDestinationDepartureDateAndStatus(
                requestModel.getSource(),
                requestModel.getDestination(),
                searchDate, // No need to parse if it's already a string format
                "ACTIVE", // Filter rides with status 'ACTIVE'
                PageRequest.of(page, size)
        );

        // If no rides are found, return a response with an empty rides list
        if (ridesPage.isEmpty()) {
            return RideSearchResultResponseModel.builder()
                    .status("No rides found")
                    .rides(List.of())
                    .build();
        }

        // Map the found rides to the response model's ride details list
        List<RideDetails> rideDetailsList = ridesPage.stream()
                .map(ride -> RideDetails.builder()
                        .source(ride.getSource())
                        .destination(ride.getDestination())
                        .ownerId(ride.getOwnerId())
                        .availableSeats(ride.getSeats())
                        .build())
                .collect(Collectors.toList());

        // Return the response model with paginated results
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
