package com.goals.repository.tree;

import com.goals.model.track.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskTreeRepository
{
    private static final Logger LOG = LogManager.getLogger();

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public TaskTreeRepository(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tasks").usingGeneratedKeyColumns("id");
    }

    @Transactional
    public void updateTask(Task foundTask)
    {
        final String UPDATE_QUERY = "UPDATE tasks " +
                "SET due_date = ?," +
                "goal_id = ?," +
                "path = ?::ltree," +
                "progress = ?," +
                "title = ? " +
                "WHERE id = ? " +
                "RETURNING id;";

        LOG.info("updateTask : updated task to save = " + foundTask.toString());

        jdbcTemplate.batchUpdate(UPDATE_QUERY, new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                ps.setObject(1, foundTask.getDueDate());
                ps.setObject(2, foundTask.getGoal().getId());
                ps.setObject(3, foundTask.getTaskPath());
                ps.setObject(4, foundTask.getProgress());
                ps.setObject(5, foundTask.getTitle());
                ps.setObject(6, foundTask.getId());
            }

            @Override
            public int getBatchSize()
            {
                return 1;
            }
        });
    }

    @Transactional
    public Long insertWithQueryPersist(Task task)
    {
        final String insertQuery = "insert into tasks (due_date, goal_id, task_path, progress, title) " +
                "values (?1, ?2, ?3\\:\\:ltree, ?4, ?5) returning id;";

        Query query = entityManager.createNativeQuery(insertQuery);
        query.setParameter(1, task.getDueDate());
        query.setParameter(2, task.getGoal().getId());
        query.setParameter(3, task.getTaskPath());
        query.setParameter(4, task.getProgress());
        query.setParameter(5, task.getTitle());
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    @Transactional
    public Long insertTask(final Task task)
    {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("due_date", task.getDueDate());
        parameters.put("goal_id", task.getGoal().getId());
        parameters.put("task_path", task.getTaskPath());
        parameters.put("progress", task.getProgress());
        parameters.put("title", task.getTitle());
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return newId.longValue();

        //KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(insertQuery, task.getDueDate()
//                , task.getGoal()
//                , task.getTaskPath()
//                , task.getProgress()
//                , task.getTitle()
//                , task.getId(), keyHolder);


        /*jdbcTemplate.update(insertQuery, new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                ps.setObject(1, task.getDueDate());
                ps.setObject(2, task.getGoal().getId());
                ps.setObject(3, task.getTaskPath());
                ps.setObject(4, task.getProgress());
                ps.setObject(5, task.getTitle());
                ps.setObject(6, task.getId());
            }

            @Override
            public int getBatchSize()
            {
                return 1;
            }
        },keyHolder);*/
    }

    @Transactional
    public void insertWithQuery(Task task)
    {
        final String insertQuery = "insert into tasks (due_date, goal_id, task_path, progress, title) " +
                "values (?, ?, ?\\:\\:ltree, ?, ?)";

        entityManager.createNativeQuery(insertQuery, Task.class)
                .setParameter(1, task.getDueDate())
                .setParameter(2, task.getGoal().getId())
                .setParameter(3, task.getTaskPath())
                .setParameter(4, task.getProgress())
                .setParameter(5, task.getTitle())
                .executeUpdate();
    }

    public List<Task> findAll()
    {
        String SELECT_QUERY = "select * from tasks;";
        Query query = entityManager.createNativeQuery(SELECT_QUERY);
        List queryList = query.getResultList();
        queryList.forEach(t -> LOG.info("findAll() : t=" + t));
        return queryList;
    }

    public List<Task> findAllTasks()
    {
        final String SELECT_QUERY = "select due_date, goal_id, cast(task_path as ltree), progress, title from tasks";
        return new ArrayList<>(jdbcTemplate.query(SELECT_QUERY, (rs, rowNum) ->
        {
            Task mappedTask = new Task();
            mappedTask.setDueDate(rs.getDate("due_date").toLocalDate());
            mappedTask.setProgress(rs.getDouble("progress"));
            mappedTask.setTaskPath(rs.getString("task_path"));
            mappedTask.setTitle(rs.getString("title"));
            return mappedTask;
        }));
    }
}
