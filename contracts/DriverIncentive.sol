// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract DriverIncentive {
    struct Driver {
        address driver;
        uint ridesCompleted;
        uint rewards;
    }

    mapping(address => Driver) public drivers;

    event RewardIssued(address driver, uint rewardAmount);

    function completeRide(address driver) public {
        drivers[driver].ridesCompleted += 1;

        if (drivers[driver].ridesCompleted % 10 == 0) {
            uint reward = drivers[driver].ridesCompleted / 10 * 1 ether; // Issue 1 ETH per 10 rides
            drivers[driver].rewards += reward;

            emit RewardIssued(driver, reward);
        }
    }

    function claimRewards() public {
        uint rewards = drivers[msg.sender].rewards;
        require(rewards > 0, "No rewards to claim");

        drivers[msg.sender].rewards = 0;
        payable(msg.sender).transfer(rewards);
    }
}
