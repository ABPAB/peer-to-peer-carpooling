package com.myroom.authserver.data.mapper;

import com.myroom.authserver.api.model.UserResponseModel;
import com.myroom.authserver.data.model.FirebaseUserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseModel toUserResponseModel(FirebaseUserModel firebaseUserModel) {
        return UserResponseModel.builder()
                .uid(firebaseUserModel.getUid())
                .email(firebaseUserModel.getEmail())
                .name(firebaseUserModel.getName())
                .isEmailVerified(firebaseUserModel.isEmailVerified())
                .providerId(firebaseUserModel.getProviderId())
                .picture(firebaseUserModel.getPicture())
                .build();
    }
}