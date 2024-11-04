const RideCreation = artifacts.require("RideCreation");

module.exports = function (deployer) {
    deployer.deploy(RideCreation, { gas: 8000000 }); // Set custom gas limit
};
