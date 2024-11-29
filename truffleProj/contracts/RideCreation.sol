// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;
pragma experimental ABIEncoderV2;

contract RideCreation {
    enum RideStatus { ACTIVE, COMPLETED, CANCELLED }
    enum RiderStatus { ACTIVE, COMPLETED, CANCELLED }

    struct RideDetails {
        string source;         // Source location
        string destination;    // Destination location
        uint fare;             // Fare for the ride
        uint availableSeats;   // Number of available seats
        string vehicleNumber;  // Vehicle number used for the ride
    }

    struct Rider {
        string riderId;        // Rider ID
        RiderStatus status;    // Rider's current status
    }

    struct Ride {
        string rideId;               // Unique identifier for the ride
        string ownerId;              // Owner ID of the ride
        RideDetails details;         // Ride details
        RideStatus status;           // Current status of the ride
        mapping(string => Rider) riders; // Mapping of Rider ID to Rider details
        string[] riderIds;           // List of Rider IDs (for iteration)
        uint256 createdAt;           // Timestamp of ride creation
        uint256 updatedAt;           // Timestamp of last update
    }

    mapping(bytes32 => Ride) private rides;

    event RideCreated(
        string rideId,
        string ownerId,
        string source,
        string destination,
        uint fare,
        uint availableSeats,
        string vehicleNumber,
        RideStatus status
    );

    event RideUpdated(
        string rideId,
        string ownerId,
        RideStatus status,
        uint availableSeats,
        uint256 updatedAt,
        uint riderCount // Number of riders in the ride
    );

    event SendNotificationEvent(
        string riderId,
        string status, // RiderStatus as string: "active", "completed", "cancelled"
        string ownerId,
        string source,
        string destination,
        uint fare,
        string vehicleNumber
    );

    // Function to create a ride
    function createRide(
    string memory rideId,
    string memory ownerId,
    string memory source,
    string memory destination,
    uint fare,
    uint availableSeats,
    string memory vehicleNumber
) public {
    // Ensure that the rideId is unique (not already used)
    bytes32 rideKey = keccak256(abi.encodePacked(rideId));
    require(bytes(rides[rideKey].rideId).length == 0, "Ride ID already exists");

    // Create the new ride by assigning individual properties
    rides[rideKey].rideId = rideId;
    rides[rideKey].ownerId = ownerId;
    rides[rideKey].details.source = source;
    rides[rideKey].details.destination = destination;
    rides[rideKey].details.fare = fare;
    rides[rideKey].details.availableSeats = availableSeats;
    rides[rideKey].details.vehicleNumber = vehicleNumber;
    rides[rideKey].status = RideStatus.ACTIVE;
    rides[rideKey].createdAt = block.timestamp;
    rides[rideKey].updatedAt = block.timestamp;

    // Emit the RideCreated event with the relevant details
    emit RideCreated(
        rideId,
        ownerId,
        source,
        destination,
        fare,
        availableSeats,
        vehicleNumber,
        RideStatus.ACTIVE
    );
}

    // Function to accept a rider into the ride by the owner
    function acceptRideByOwner(
        string memory rideId,
        string memory ownerId,
        string memory riderId
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];

        // Check if the ride exists
        require(bytes(ride.rideId).length > 0, "Ride does not exist");

        // Check if the caller is the owner of the ride
        require(keccak256(abi.encodePacked(ride.ownerId)) == keccak256(abi.encodePacked(ownerId)), "Unauthorized: Only the ride owner can accept a rider");

        // Check if the ride is in ACTIVE status
        require(ride.status == RideStatus.ACTIVE, "Ride is not ACTIVE and cannot accept riders");

        // Check if there are available seats
        require(ride.details.availableSeats > 0, "No available seats");

        // Check if the rider is already in the ride
        require(bytes(ride.riders[riderId].riderId).length == 0, "Rider is already part of the ride");

        // Add the rider to the mapping and array
        ride.riders[riderId] = Rider(riderId, RiderStatus.ACTIVE);
        ride.riderIds.push(riderId);

        // Decrease the available seats
        ride.details.availableSeats -= 1;

        // Update the last updated timestamp
        ride.updatedAt = block.timestamp;

        // Emit RideUpdated event
        emit RideUpdated(
            ride.rideId,
            ride.ownerId,
            ride.status,
            ride.details.availableSeats,
            ride.updatedAt,
            ride.riderIds.length
        );

        // Emit SendNotificationEvent for the added rider
        emit SendNotificationEvent(
            riderId,
            "active",
            ride.ownerId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.vehicleNumber
        );
    }

    // Function to update the ride status by the owner (e.g., for completing or canceling a ride)
    function updateRideStatusByDriver(
        string memory rideId,
        string memory ownerId,
        RideStatus status
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];

        // Check if the ride exists
        require(bytes(ride.rideId).length > 0, "Ride does not exist");

        // Check if the caller is the owner of the ride
        require(keccak256(abi.encodePacked(ride.ownerId)) == keccak256(abi.encodePacked(ownerId)), "Unauthorized: Only the ride owner can update the status");

        // Check if the ride is ACTIVE
        require(ride.status == RideStatus.ACTIVE, "Ride is not in an ACTIVE state");

        // Update RideStatus and perform actions based on the new status
        if (status == RideStatus.CANCELLED) {
            ride.status = RideStatus.CANCELLED;

            // Emit events for all active riders
            for (uint i = 0; i < ride.riderIds.length; i++) {
                string memory riderId = ride.riderIds[i];
                if (ride.riders[riderId].status == RiderStatus.ACTIVE) {
                    emit SendNotificationEvent(
                        riderId,
                        "cancelled",
                        ride.ownerId,
                        ride.details.source,
                        ride.details.destination,
                        ride.details.fare,
                        ride.details.vehicleNumber
                    );
                }
            }
        } else if (status == RideStatus.COMPLETED) {
            ride.status = RideStatus.COMPLETED;

            // Update all ACTIVE riders to COMPLETED and emit events
            for (uint i = 0; i < ride.riderIds.length; i++) {
                string memory riderId = ride.riderIds[i];
                if (ride.riders[riderId].status == RiderStatus.ACTIVE) {
                    ride.riders[riderId].status = RiderStatus.COMPLETED;

                    emit SendNotificationEvent(
                        riderId,
                        "completed",
                        ride.ownerId,
                        ride.details.source,
                        ride.details.destination,
                        ride.details.fare,
                        ride.details.vehicleNumber
                    );
                }
            }
        } else {
            revert("Invalid RideStatus");
        }

        // Update the last updated timestamp
        ride.updatedAt = block.timestamp;

        // Emit RideUpdated event
        emit RideUpdated(
            ride.rideId,
            ride.ownerId,
            ride.status,
            ride.details.availableSeats,
            ride.updatedAt,
            ride.riderIds.length
        );
    }

    // Function to cancel the ride for a rider
    function cancelRideByRider(
        string memory rideId,
        string memory riderId
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];

        // Check if the ride exists
        require(bytes(ride.rideId).length > 0, "Ride does not exist");

        // Check if the rider exists in the ride
        require(bytes(ride.riders[riderId].riderId).length > 0, "Rider is not part of the ride");

        // Check if the rider is ACTIVE
        require(ride.riders[riderId].status == RiderStatus.ACTIVE, "Rider is not in an ACTIVE state");

        // Cancel the rider from the ride
        ride.riders[riderId].status = RiderStatus.CANCELLED;

        // Increment the available seats
        ride.details.availableSeats += 1;

        // Update the last updated timestamp
        ride.updatedAt = block.timestamp;

        // Emit RideUpdated event
        emit RideUpdated(
            ride.rideId,
            ride.ownerId,
            ride.status,
            ride.details.availableSeats,
            ride.updatedAt,
            ride.riderIds.length
        );

        // Emit SendNotificationEvent for the cancelled rider
        emit SendNotificationEvent(
            riderId,
            "cancelled",
            ride.ownerId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.vehicleNumber
        );
    }

    function getRiderStatus(string memory rideId, string memory riderId) public view returns (RiderStatus) {
    bytes32 rideKey = keccak256(abi.encodePacked(rideId));
    require(bytes(rides[rideKey].rideId).length > 0, "Ride does not exist");
    require(bytes(rides[rideKey].riders[riderId].riderId).length > 0, "Rider does not exist");
    return rides[rideKey].riders[riderId].status;
    }

}
