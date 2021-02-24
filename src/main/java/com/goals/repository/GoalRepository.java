package com.goals.repository;

import com.goals.model.track.Goal;
import com.goals.model.track.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID>
{
    @Query("SELECT g FROM goals g JOIN FETCH g.user u WHERE u.email= :email")
    List<Goal> findByUserEmail(String email);
}
