// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract RefundAndDispute {
    address public admin;

    struct Dispute {
        uint rideId;
        address rider;
        string reason;
        bool resolved;
        bool refundIssued;
    }

    mapping(uint => Dispute) public disputes;

    event RefundRequested(uint rideId, address rider, string reason);
    event DisputeResolved(uint rideId, bool refundIssued);

    constructor() {
        admin = msg.sender;
    }

    function requestRefund(uint rideId, string memory reason) public {
        disputes[rideId] = Dispute(rideId, msg.sender, reason, false, false);

        emit RefundRequested(rideId, msg.sender, reason);
    }

    function resolveDispute(uint rideId, bool issueRefund) public {
        require(msg.sender == admin, "Only admin can resolve disputes");

        Dispute storage dispute = disputes[rideId];
        require(!dispute.resolved, "Dispute already resolved");

        dispute.resolved = true;
        dispute.refundIssued = issueRefund;

        if (issueRefund) {
            payable(dispute.rider).transfer(address(this).balance);
        }

        emit DisputeResolved(rideId, issueRefund);
    }
}
