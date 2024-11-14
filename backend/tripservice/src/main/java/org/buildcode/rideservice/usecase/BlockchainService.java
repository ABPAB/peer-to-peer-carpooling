package org.buildcode.rideservice.usecase;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

public interface BlockchainService {

    TransactionReceipt createRide();
}