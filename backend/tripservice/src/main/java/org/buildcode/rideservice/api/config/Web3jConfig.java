package org.buildcode.rideservice.api.config;

import org.buildcode.rideservice.api.constants.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    @Bean
    public Web3j web3j() {
        return Web3j.build(
                new HttpService(ApiConstants.BLOCKCHAIN_BASE_URL_V1)
        );
    }
}