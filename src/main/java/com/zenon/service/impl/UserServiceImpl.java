package com.zenon.service.impl;

import com.zenon.dto.UserDto;
import com.zenon.exception.UserNotFoundException;
import com.zenon.mapper.UserMapper;
import com.zenon.model.User;
import com.zenon.repository.UserRepository;
import com.zenon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<UserDto> list(Pageable pageable) {
        if(Objects.isNull(pageable)) {
            pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "username");
        }
        return userRepository
                .findAll(pageable)
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto save(UserDto userDto) {
        return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(userDto)));
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username,password)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
