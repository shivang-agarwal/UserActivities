package com.zenon.mapper;

import com.zenon.dto.ActivityDto;
import com.zenon.model.Activity;

import java.util.Objects;

public class ActivityMapper {
    private ActivityMapper() {}

    public static ActivityDto activityDto(Activity activity) {
        return new ActivityDto()
                .setId(activity.getId())
                .setName(activity.getName())
                .setTargetDate(activity.getTargetDate())
                .setDescription(activity.getDescription());
    }

    public static Activity activity(ActivityDto activityDto) {
        return new Activity()
                .setId(activityDto.getId())
                .setName(activityDto.getName())
                .setTargetDate(activityDto.getTargetDate())
                .setDescription(activityDto.getDescription())
                .setUser(activityDto.getUser());
    }

    public static Activity dtoActivity(Activity activity, ActivityDto activityDto) {
        if (Objects.nonNull(activityDto.getName())) {
            activity.setName(activityDto.getName());
        }
        if (Objects.nonNull(activityDto.getTargetDate())) {
            activity.setTargetDate(activityDto.getTargetDate());
        }
        if (Objects.nonNull(activityDto.getDescription())) {
            activity.setDescription(activityDto.getDescription());
        }
        return activity;
    }
}
