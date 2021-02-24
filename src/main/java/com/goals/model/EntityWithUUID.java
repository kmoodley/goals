package com.goals.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class EntityWithUUID
{
    @Id
    //@Column(columnDefinition = "uuid", updatable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    public EntityWithUUID()
    {
        this.id = UUID.randomUUID();
    }

    public UUID getId()
    {
        return id;
    }
}
