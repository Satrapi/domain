package com.artronics.sdwn.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sessions")
public class NetworkSession implements Serializable
{
    private Long id;

    private Status status=Status.ACTIVE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "status",nullable = false,unique = false)
    @Enumerated(EnumType.STRING)
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public enum Status
    {
        ACTIVE,
        EXPIRED
    }
}
