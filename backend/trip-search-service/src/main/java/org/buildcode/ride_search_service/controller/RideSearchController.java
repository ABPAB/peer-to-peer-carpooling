package org.buildcode.ride_search_service.controller;

import org.buildcode.ride_search_service.api.constants.RideStatus;
import org.buildcode.ride_search_service.api.model.v1_0.RideSearchRequestModel;
import org.buildcode.ride_search_service.api.model.v1_0.RideSearchResultResponseModel;
import org.buildcode.ride_search_service.api.resources.RideSearchResource;
import org.buildcode.ride_search_service.repository.RideRepository;
import org.buildcode.ride_search_service.service.RideSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RideSearchController implements RideSearchResource {
    @Autowired
    private RideSearchService rideSearchService;

    @Autowired
    RideRepository rideRepository;

    @Override
    public ResponseEntity<RideSearchResultResponseModel> getRides(@RequestBody RideSearchRequestModel searchRequestModel, @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "3") int size) {
        System.out.println("before request....");
        RideSearchResultResponseModel response = rideSearchService.searchRides(searchRequestModel, page, size);
        System.out.println("Got here.....");
        return ResponseEntity.ok(response);
    }
}
