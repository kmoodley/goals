package com.goals.repository;

import com.goals.model.track.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long>
{
}
