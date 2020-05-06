package com.zenon.repository;

import com.zenon.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

//    @Query("from Activity where isDeleted = ?1")
    public Page<Activity> findAllByIsDeleted(Pageable pageable, boolean isDeleted);
}
