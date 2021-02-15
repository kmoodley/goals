package com.goals.model.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.goals.model.EntityWithUUID;
import com.goals.model.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity(name = "goals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Goal extends EntityWithUUID
{
    private String title;

    private String description;

    private LocalDate dueDate;

    private Double progress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Override
    public String toString()
    {
        return "Goal{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", progress=" + progress +
                '}';
    }
}
