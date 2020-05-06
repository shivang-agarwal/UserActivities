package com.zenon.service.impl;

import com.zenon.dto.ActivityDto;
import com.zenon.exception.ActivityNotFoundException;
import com.zenon.mapper.ActivityMapper;
import com.zenon.model.User;
import com.zenon.repository.ActivityRepository;
import com.zenon.service.ActivityService;
import com.zenon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @Override
    public Set<ActivityDto> list(Pageable pageable, User user) {
        if(Objects.isNull(pageable)) {
            pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "targetDate");
        }
        return activityRepository
                .findAllByIsDeletedAndUser(pageable,false, user)
                .stream()
                .map(ActivityMapper::activityDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ActivityDto save(ActivityDto activityDto) {
        return ActivityMapper.activityDto(activityRepository.save(ActivityMapper.activity(activityDto)));
    }

    @Override
    public ActivityDto update(ActivityDto activityDto) {
        return activityRepository
                .findByIdAndUser(activityDto.getId(), activityDto.getUser())
                .map( activity -> ActivityMapper.activityDto(activityRepository.save(ActivityMapper.dtoActivity(activity, activityDto))))
                .orElseThrow(() -> new ActivityNotFoundException(activityDto.getId()));
    }


    @Override
    public void delete(String id, User user) {
        activityRepository.findByIdAndUser(id, user)
        .map(activity -> {
            activity.setIsDeleted(true);
            return activityRepository.save(activity);
        }).orElseThrow(() -> new ActivityNotFoundException(id));
    }
}
