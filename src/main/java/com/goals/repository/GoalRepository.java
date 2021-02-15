package com.goals.repository;

import com.goals.model.track.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long>
{
    @Query("SELECT g FROM goals g JOIN FETCH g.user u WHERE u.email= :email")
    List<Goal> findByUserEmail(String email);
}
