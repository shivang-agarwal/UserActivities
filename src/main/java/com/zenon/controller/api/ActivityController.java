package com.zenon.controller.api;

import com.zenon.dto.ActivityDto;
import com.zenon.model.User;
import com.zenon.service.ActivityService;
import com.zenon.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Api(tags = "Activities")
@RestController()
@RequestMapping("/api/v1/activities")
public class ActivityController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    private final ActivityService activityService;
    private final UserService userService;

    @Autowired
    public ActivityController(ActivityService activityService, UserService userService){
        this.activityService = activityService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ActivityDto> save(@RequestBody ActivityDto activityDto,
                                            @RequestHeader(required = true) String username,
                                            @RequestHeader(required = true) String password){
        authenticate(activityDto, username, password);
        return new ResponseEntity<ActivityDto>(
                activityService.save(activityDto),
                HttpStatus.OK
        );
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityDto> update(@PathVariable("activityId") String id, @RequestBody ActivityDto activityDto,
                                              @RequestHeader(required = true) String username,
                                              @RequestHeader(required = true) String password){
        authenticate(activityDto, username, password);
        return new ResponseEntity<ActivityDto>(
                activityService.update(activityDto.setId(id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity delete(@PathVariable("activityId") String id,
                                 @RequestHeader(required = true) String username,
                                 @RequestHeader(required = true) String password){
        authenticate(username, password);
        activityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<Set<ActivityDto>> list(@PageableDefault(page = 0,size = 10)
                                                     @SortDefault.SortDefaults(@SortDefault(sort = "targetDate", direction = Sort.Direction.ASC)) Pageable pageable,
            @RequestHeader(required = true) String username,
            @RequestHeader(required = true) String password) {
        authenticate(username, password);
        return new ResponseEntity<Set<ActivityDto>>(
                activityService.list(pageable),
                HttpStatus.OK
        );
    }

    private void authenticate(ActivityDto activityDto, String username, String password){
        activityDto.setUser(authenticate(username,password));
    }

    private User authenticate(String username, String password){
        return userService.findByUsernameAndPassword(username,password);
    }
}
