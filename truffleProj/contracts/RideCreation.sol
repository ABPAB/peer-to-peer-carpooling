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
        string departureTime;  // Departure time
        string departureDate;  // Departure date
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
        string departureTime,
        string departureDate,
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
        string vehicleNumber,
        string departureTime, // Add departureTime
        string departureDate  // Add departureDate
    );

    // Function to create a ride
    function createRide(
        string memory rideId,
        string memory ownerId,
        string memory source,
        string memory destination,
        uint fare,
        uint availableSeats,
        string memory vehicleNumber,
        string memory departureTime,
        string memory departureDate
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        require(bytes(rides[rideKey].rideId).length == 0, "Ride ID already exists");

        rides[rideKey].rideId = rideId;
        rides[rideKey].ownerId = ownerId;
        rides[rideKey].details.source = source;
        rides[rideKey].details.destination = destination;
        rides[rideKey].details.fare = fare;
        rides[rideKey].details.availableSeats = availableSeats;
        rides[rideKey].details.vehicleNumber = vehicleNumber;
        rides[rideKey].details.departureTime = departureTime;
        rides[rideKey].details.departureDate = departureDate;
        rides[rideKey].status = RideStatus.ACTIVE;
        rides[rideKey].createdAt = block.timestamp;
        rides[rideKey].updatedAt = block.timestamp;

        emit RideCreated(
            rideId,
            ownerId,
            source,
            destination,
            fare,
            availableSeats,
            vehicleNumber,
            departureTime,
            departureDate,
            RideStatus.ACTIVE
        );
    }

    function acceptRideByOwner(
        string memory rideId,
        string memory ownerId,
        string memory riderId
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];

        require(bytes(ride.rideId).length > 0, "Ride does not exist");
        require(keccak256(abi.encodePacked(ride.ownerId)) == keccak256(abi.encodePacked(ownerId)), "Unauthorized");
        require(ride.status == RideStatus.ACTIVE, "Ride is not ACTIVE");
        require(ride.details.availableSeats > 0, "No available seats");
        require(bytes(ride.riders[riderId].riderId).length == 0, "Rider already in ride");

        ride.riders[riderId] = Rider(riderId, RiderStatus.ACTIVE);
        ride.riderIds.push(riderId);
        ride.details.availableSeats -= 1;
        ride.updatedAt = block.timestamp;

        emit RideUpdated(
            ride.rideId,
            ride.ownerId,
            ride.status,
            ride.details.availableSeats,
            ride.updatedAt,
            ride.riderIds.length
        );

        emit SendNotificationEvent(
            riderId,
            "active",
            ride.ownerId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.vehicleNumber,
            ride.details.departureTime,
            ride.details.departureDate
        );
    }

    function updateRideStatusByDriver(
        string memory rideId,
        string memory ownerId,
        RideStatus status
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];

        require(bytes(ride.rideId).length > 0, "Ride does not exist");
        require(keccak256(abi.encodePacked(ride.ownerId)) == keccak256(abi.encodePacked(ownerId)), "Unauthorized");
        require(ride.status == RideStatus.ACTIVE, "Ride is not ACTIVE");

        if (status == RideStatus.CANCELLED) {
            ride.status = RideStatus.CANCELLED;
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
                        ride.details.vehicleNumber,
                        ride.details.departureTime,
                        ride.details.departureDate
                    );
                }
            }
        } else if (status == RideStatus.COMPLETED) {
            ride.status = RideStatus.COMPLETED;
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
                        ride.details.vehicleNumber,
                        ride.details.departureTime,
                        ride.details.departureDate
                    );
                }
            }
        } else {
            revert("Invalid RideStatus");
        }

        ride.updatedAt = block.timestamp;

        emit RideUpdated(
            ride.rideId,
            ride.ownerId,
            ride.status,
            ride.details.availableSeats,
            ride.updatedAt,
            ride.riderIds.length
        );
    }

    function cancelRideByRider(
        string memory rideId,
        string memory riderId
    ) public {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        Ride storage ride = rides[rideKey];

        require(bytes(ride.rideId).length > 0, "Ride does not exist");
        require(bytes(ride.riders[riderId].riderId).length > 0, "Rider is not part of the ride");
        require(ride.riders[riderId].status == RiderStatus.ACTIVE, "Rider is not ACTIVE");

        ride.riders[riderId].status = RiderStatus.CANCELLED;
        ride.details.availableSeats += 1;
        ride.updatedAt = block.timestamp;

        emit RideUpdated(
            ride.rideId,
            ride.ownerId,
            ride.status,
            ride.details.availableSeats,
            ride.updatedAt,
            ride.riderIds.length
        );

        emit SendNotificationEvent(
            riderId,
            "cancelled",
            ride.ownerId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.vehicleNumber,
            ride.details.departureTime,
            ride.details.departureDate
        );
    }

    function getRiderStatus(string memory rideId, string memory riderId) public view returns (RiderStatus) {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        require(bytes(rides[rideKey].rideId).length > 0, "Ride does not exist");
        require(bytes(rides[rideKey].riders[riderId].riderId).length > 0, "Rider does not exist");
        return rides[rideKey].riders[riderId].status;
    }

    function getRideDetails(string memory rideId)
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
            string memory,
            string memory,
            RideStatus
        )
    {
        bytes32 rideKey = keccak256(abi.encodePacked(rideId));
        require(bytes(rides[rideKey].rideId).length > 0, "Ride does not exist");

        Ride storage ride = rides[rideKey];

        return (
            ride.rideId,
            ride.ownerId,
            ride.details.source,
            ride.details.destination,
            ride.details.fare,
            ride.details.availableSeats,
            ride.details.vehicleNumber,
            ride.details.departureTime,
            ride.details.departureDate,
            ride.status
        );
    }
}