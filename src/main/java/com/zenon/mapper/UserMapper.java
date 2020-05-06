package com.zenon.mapper;

import com.zenon.dto.UserDto;
import com.zenon.model.User;

public class UserMapper {
    private UserMapper(){}

    public static UserDto toUserDto(User user){
        return new UserDto()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword());
    }

    public static User toUser(UserDto userDto) {
        return new User()
                .setPassword(userDto.getPassword())
                .setUsername(userDto.getUsername());
    }
}
