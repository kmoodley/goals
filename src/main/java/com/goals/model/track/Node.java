package com.goals.model.track;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tree")
public class Node
{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name = "parent_id")
    @OrderColumn
    private List<Node> children = new LinkedList<Node>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Node> getChildren()
    {
        return children;
    }

    public void setChildren(List<Node> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
