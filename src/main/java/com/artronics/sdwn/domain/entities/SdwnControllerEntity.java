package com.artronics.sdwn.domain.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="controllers")
public class SdwnControllerEntity implements Serializable
{
    private Long id;

    private String url;

    private Status status = Status.ACTIVE;

    private String description;

    private List<SwitchingNetwork> switchingNetworks = new ArrayList<>();

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

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<SwitchingNetwork> getSwitchingNetworks()
    {
        return switchingNetworks;
    }

    public void setSwitchingNetworks(
            List<SwitchingNetwork> switchingNetworks)
    {
        this.switchingNetworks = switchingNetworks;
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

    public void addSwitchingNet(SwitchingNetwork net){
        switchingNetworks.add(net);
    }

    public enum Status{
        ACTIVE
    }
}
