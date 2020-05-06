package com.zenon.service;

import com.zenon.dto.ActivityDto;
import com.zenon.model.User;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ActivityService {
    public Set<ActivityDto> list(Pageable pageable, User user);
    public ActivityDto save(ActivityDto activityDto);
    public ActivityDto update(ActivityDto activityDto);
    public void delete(String id, User user);
}
