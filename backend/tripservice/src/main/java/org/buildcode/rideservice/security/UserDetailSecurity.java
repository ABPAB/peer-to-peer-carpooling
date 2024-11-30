package org.buildcode.rideservice.security;

import lombok.Data;

@Data
public class UserDetailSecurity {

    private String uid;

    private String name;

    private String email;

    private boolean isEmailVerified;

    private String providerId;

    private String picture;
}