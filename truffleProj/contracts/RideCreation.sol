// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract RideCreation {
    struct Ride {
        uint rideId;
        address driver;
        string pickupLocation;
        string dropoffLocation;
        uint fare;
        uint availableSeats;
        bool isActive;
    }

    uint public rideCounter = 0;
    mapping(uint => Ride) public rides;

    event RideCreated(uint rideId, address driver, string pickupLocation, string dropoffLocation, uint fare, uint availableSeats);

    function createRide(string memory pickupLocation, string memory dropoffLocation, uint fare, uint availableSeats) public {
        require(availableSeats > 0, "There must be at least one available seat");

        rideCounter++;
        rides[rideCounter] = Ride(rideCounter, msg.sender, pickupLocation, dropoffLocation, fare, availableSeats, true);

        emit RideCreated(rideCounter, msg.sender, pickupLocation, dropoffLocation, fare, availableSeats);
    }

    function getRide(uint rideId) public view returns (Ride memory) {
        return rides[rideId];
    }
}
