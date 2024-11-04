// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract Reputation {
    struct Review {
        uint rideId;
        address reviewer;
        address reviewee;
        uint rating; // rating out of 5
        string comment;
    }

    mapping(address => uint) public reputationScores;
    mapping(uint => Review[]) public rideReviews;

    event ReviewSubmitted(uint rideId, address reviewer, address reviewee, uint rating, string comment);

    function submitReview(uint rideId, address reviewee, uint rating, string memory comment) public {
        require(rating <= 5, "Rating must be between 1 and 5");

        rideReviews[rideId].push(Review(rideId, msg.sender, reviewee, rating, comment));
        reputationScores[reviewee] = (reputationScores[reviewee] + rating) / 2;

        emit ReviewSubmitted(rideId, msg.sender, reviewee, rating, comment);
    }

    function getReputation(address user) public view returns (uint) {
        return reputationScores[user];
    }
}
