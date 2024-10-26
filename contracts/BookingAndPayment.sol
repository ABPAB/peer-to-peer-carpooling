// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BookingAndPayment {

    struct Booking {
        uint rideId;
        address rider;
        uint seatsBooked;
        uint amountPaid;
        bool isConfirmed;
    }

    mapping(uint => Booking[]) public rideBookings;
    mapping(address => uint) public balances;

    event RideBooked(uint rideId, address rider, uint seats, uint amountPaid);
    event PaymentReleased(address driver, uint amount);

    // Function to book a ride, with seats and fare amount
    function bookRide(uint rideId, uint seats, uint farePerSeat) public payable {
        require(msg.value == seats * farePerSeat, "Incorrect payment amount");

        // Save the booking details
        rideBookings[rideId].push(Booking(rideId, msg.sender, seats, msg.value, true));
        balances[msg.sender] = msg.value;

        emit RideBooked(rideId, msg.sender, seats, msg.value);
    }

    // Function to release payment to the driver
    function releasePayment(address driver, uint rideId) public {
        uint amount = balances[driver];
        require(amount > 0, "No balance to release");

        balances[driver] = 0;
        payable(driver).transfer(amount);

        emit PaymentReleased(driver, amount);
    }
}
