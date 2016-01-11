package com.artronics.sdwn.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
public class NetworkSession
{
    private Long id;

    private Status status;

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