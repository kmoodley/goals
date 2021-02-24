package com.goals.repository.tree;

import com.goals.model.track.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, Long>
{
    String INSERT_QUERY = "insert into tasks (due_date, goal_id, task_path, progress, title) " +
            "values (?1, ?2, ?3\\:\\:ltree, ?4, ?5) returning id;";

    String UPDATE_QUERY = "UPDATE tasks " +
            "SET due_date = :due_date," +
            "goal_id = :goal_id," +
            "path = :path\\:\\:ltree," +
            "progress = :progress," +
            "title = :title " +
            "WHERE id = :id " +
            "RETURNING id;";


    List<Task> findByGoalId(UUID goalId);



    @Transactional
    @Query(value = INSERT_QUERY, nativeQuery = true)
    Long insertTask(LocalDate dueDate, UUID goalId, String path, Double progress, String title);

    String UPDATE_TITLE_QUERY = "UPDATE tasks " +
            "SET title = :title " +
            "WHERE id = :task_id " +
            "RETURNING id;";
    @Query(value = UPDATE_TITLE_QUERY, nativeQuery = true)
    void updateTaskTitle(@Param("task_id") Long id, @Param("title") String title);

    /*@Transactional
    @Query(value = UPDATE_QUERY, nativeQuery = true)
    Long updateTask(@Param("due_date") LocalDate dueDate,
                    @Param("goal_id") UUID goalId,
                    @Param("path") String path,
                    @Param("progress") Double progress,
                    @Param("title") String title,
                    @Param("id") Long whereId);*/
}
