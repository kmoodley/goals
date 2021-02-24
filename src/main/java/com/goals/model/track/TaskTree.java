package com.goals.model.track;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name="plain_tree")
@Data
public class TaskTree
{
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "letter")
    private String letter;

    @Column(name = "path", nullable = false, columnDefinition = "ltree")
    private String path;
}