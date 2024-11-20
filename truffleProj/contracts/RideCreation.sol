// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;
pragma experimental ABIEncoderV2;

contract RideCreation {
    enum RideStatus { ACTIVE, COMPLETED, CANCELLED }

    struct RideDetails {
        string source;        // Source location
        string destination;   // Destination location
        uint fare;            // Fare for the ride
        uint availableSeats;  // Number of available seats
        string carModel;      // Car model used for the ride
    }

    struct Ride {
        string rideId;         // Unique identifier for the ride
        string userId;         // User ID of the driver
        RideDetails details;   // Ride details
        RideStatus status;     // Current status of the ride
        uint256 createdAt;     // Timestamp of ride creation
        uint256 updatedAt;     // Timestamp of last update
    }

    struct RideInput {
        string rideId;
        string userId;
        string source;
        string destination;
        uint fare;
        uint availableSeats;
        string carModel;
    }

    mapping(bytes32 => Ride) private rides;

    event RideCreated(
        string rideId,
        string userId,
        string source,
        string destination,
        uint fare,
        uint availableSeats,
        string carModel,
        RideStatus status
    );

    event RideUpdated(
        string rideId,
        string userId,
        string source,
        string destination,
        uint fare,
        uint availableSeats,
        string carModel,
        RideStatus status,
        uint256 updatedAt
    );

    /**
     * @dev Create a new ride using a struct as input.
     * @param rideInput Struct containing all ride creation parameters.
     */
    function createRide(RideInput memory rideInput) public {
        require(bytes(rideInput.rideId).length > 0, "Ride ID cannot be empty");
        require(rideInput.availableSeats > 0, "There must be at least one available seat");
        require(bytes(rideInput.userId).length > 0, "User ID cannot be empty");

        bytes32 rideKey = keccak256(abi.encodePacked(rideInput.rideId));
        require(bytes(rides[rideKey].rideId).length == 0, "Ride ID already exists");

        rides[rideKey] = Ride(
            rideInput.rideId,
            rideInput.userId,
            RideDetails(rideInput.source, rideInput.destination, rideInput.fare, rideInput.availableSeats, rideInput.carModel),
            RideStatus.ACTIVE,
            block.timestamp,
            block.timestamp
        );

        emit RideCreated(
            rideInput.rideId,
            rideInput.userId,
            rideInput.source,
            rideInput.destination,
            rideInput.fare,
            rideInput.availableSeats,
            rideInput.carModel,
            RideStatus.ACTIVE
        );
    }

    /**
     * @dev Retrieve ride details by ID.
     * @param rideId ID of the ride to retrieve.
     */
    function getRide(string memory rideId)
        public
        view
        returns (
            string memory,
            string memory,
            string memory,
            string memory,
            uint,
            uint,
            string memory,
            RideStatus,
            uint256,
            uint256
        )
    {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];
        require(bytes(ride.rideId).length > 0, "Ride does not exist");

        return (
            ride.rideId,
            ride.userId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.availableSeats,
            ride.details.carModel,
            ride.status,
            ride.createdAt,
            ride.updatedAt
        );
    }

    /**
     * @dev Update the status of a ride.
     * @param rideId ID of the ride to update.
     * @param status New status for the ride.
     */
    function updateRideStatus(string memory rideId, RideStatus status) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];
        require(bytes(ride.rideId).length > 0, "Ride does not exist");

        ride.status = status;
        ride.updatedAt = block.timestamp;

        emit RideUpdated(
            ride.rideId,
            ride.userId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.availableSeats,
            ride.details.carModel,
            ride.status,
            ride.updatedAt
        );
    }
}
