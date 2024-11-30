package org.buildcode.rideservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.data.dto.UserResponseModel;
import org.buildcode.rideservice.usecase.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class VerifyUserDetailsFilter extends OncePerRequestFilter {

    private final AuthService authService;

    private final UserInfoContext userInfoContext;

    @Autowired
    public VerifyUserDetailsFilter(AuthService authService, UserInfoContext userInfoContext) {
        this.authService = authService;
        this.userInfoContext = userInfoContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract the bearer token from the Authorization header
        String bearerToken = extractBearerToken(request);

        System.out.println("Filter called...");
        System.out.println("got the token: " + bearerToken);

        if (bearerToken != null && !bearerToken.isEmpty()) {
            try {
                // Use the AuthService to verify the token and get user details
                UserResponseModel userResponse = authService.verifyTokenAndGetUserDetails(bearerToken);

                System.out.println("Got the userResponse from authservice");
                System.out.println(userResponse);

                // Populate UserInfoContext with the user details
                if (userResponse != null) {
                    UserDetailSecurity userDetailSecurity = new UserDetailSecurity();
                    userDetailSecurity.setUid(userResponse.getUid());
                    userDetailSecurity.setName(userResponse.getName());
                    userDetailSecurity.setEmail(userResponse.getEmail());
                    userDetailSecurity.setProviderId(userResponse.getProviderId());
                    userDetailSecurity.setPicture(userResponse.getPicture());

                    userInfoContext.setUserDetailSecurity(userDetailSecurity);

                    log.info("Successfully set the user details in user context!");
                    log.info("User details: {}", userDetailSecurity);
                }

            } catch (Exception e) {
                // Handle errors gracefully (log or rethrow as necessary)
                log.error("Error verifying user details: {}", e.getMessage(), e);
                // You can throw a custom exception or return an error response here if needed
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}