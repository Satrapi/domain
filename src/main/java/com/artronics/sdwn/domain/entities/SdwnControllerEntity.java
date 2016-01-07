package com.artronics.sdwn.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="controllers")
public class SdwnControllerEntity implements Serializable
{
    private Long id;

    private String url;

    private Status status = Status.ACTIVE;

    private String description;

    protected Date created;
    protected Date updated;

    public SdwnControllerEntity()
    {
    }

    public SdwnControllerEntity(String url)
    {
        this.url = url;
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @Column(name = "description")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "url",nullable = false,unique = true)
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @PrePersist
    protected void onCreate()
    {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updated = new Date();
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public enum Status{
        ACTIVE
    }
}
