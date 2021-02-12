package com.goals.model.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.goals.model.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "goals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@ToString
public class Goal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long goalId;

    private String goalTitle;

    private String goalDescription;

    private LocalDate deadlineDate;

    private Double progress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
