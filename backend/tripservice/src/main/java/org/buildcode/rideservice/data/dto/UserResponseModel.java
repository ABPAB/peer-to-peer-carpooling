package org.buildcode.rideservice.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseModel {
    private String uid;

    private String name;

    private String email;

    private boolean isEmailVerified;

    private String providerId;

    private String picture;
}