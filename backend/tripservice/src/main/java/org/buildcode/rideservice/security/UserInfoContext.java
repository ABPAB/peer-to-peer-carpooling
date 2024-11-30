package org.buildcode.rideservice.security;


public class UserInfoContext {

    private InheritableThreadLocal<UserDetailSecurity> userDetailStorage = new InheritableThreadLocal<>();

    public UserDetailSecurity getUserDetails() {
        return userDetailStorage.get();
    }

    public void setUserDetailSecurity(UserDetailSecurity userDetailSecurity) {
        userDetailStorage.set(userDetailSecurity);
    }

    public void clearUserInfo() {
        userDetailStorage.remove();  // Clear the context after request processing
    }
}