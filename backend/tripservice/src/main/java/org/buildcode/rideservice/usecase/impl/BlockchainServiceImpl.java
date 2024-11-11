package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {


    @Override
    public TransactionReceipt createRide() {
        // create block for new ride
        // blockchain codes...
        return null;
    }
}