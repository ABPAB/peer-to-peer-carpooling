package org.buildcode.rideservice.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.buildcode.rideservice.data.dto.UserResponseModel;
import org.buildcode.rideservice.usecase.AuthService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public UserResponseModel verifyTokenAndGetUserDetails(String bearerToken) {
        try {
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("http://localhost:8083/api/v1/auth/getUserDetails")
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + bearerToken)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                UserResponseModel userResponseModel = objectMapper.readValue(responseBody, UserResponseModel.class);
                log.info("user details is :{}", userResponseModel);
                return userResponseModel;
            } else {
                log.error("Failed to verify token: " + response.message());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while verifying token and fetching user details", e);
            return null;
        }
    }
}