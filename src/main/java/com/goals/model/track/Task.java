package com.goals.model.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "tasks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Task
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate dueDate;

    private Double progress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    @JsonIgnore
    private Goal goal;

    @Column(columnDefinition = "ltree")
    private String taskPath;

    @Override
    public String toString()
    {
        return "Task{" +
                "title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", progress=" + progress +
                ", path=" + taskPath +
                '}';
    }
}
