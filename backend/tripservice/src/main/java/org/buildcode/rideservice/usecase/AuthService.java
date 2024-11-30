package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.data.dto.UserResponseModel;

public interface AuthService {

    UserResponseModel verifyTokenAndGetUserDetails(String bearerToken);
}