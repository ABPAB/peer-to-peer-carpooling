package org.buildcode.rideservice.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ethereum")
public class EthereumConfig {

    private String contractAddress;

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    private String blockchainKey;

    public String getBlockchainKey(){
        return blockchainKey;
    }

    public void setBlockChainKey(String blockchainKey){
        this.blockchainKey = blockchainKey;
    }
}
