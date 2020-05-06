package com.zenon.controller.api;

import com.zenon.dto.ActivityDto;
import com.zenon.dto.UserDto;
import com.zenon.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Api(tags = "Users")
@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(
                userService.save(userDto),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<Set<UserDto>> list(@PageableDefault(page = 0,size = 10)
                                                 @SortDefault.SortDefaults(@SortDefault(sort = "username", direction = Sort.Direction.ASC)) Pageable pageable) {
        return new ResponseEntity<Set<UserDto>>(
                userService.list(pageable),
                HttpStatus.OK
        );
    }
}
