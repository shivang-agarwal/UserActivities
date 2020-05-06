package com.zenon.service;

import com.zenon.dto.ActivityDto;
import com.zenon.dto.UserDto;
import com.zenon.model.User;
import org.springframework.data.domain.Pageable;


import java.util.Set;

public interface UserService {
    public Set<UserDto> list(Pageable pageable);
    public UserDto save(UserDto userDto);
    public User findByUsernameAndPassword(String username, String password);
}
