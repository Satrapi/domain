package com.artronics.sdwn.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "switching_net")
public class SwitchingNetwork implements Serializable
{
    private  Long id;

    private String url;

    private String description;

    private SdwnControllerEntity sdwnController;

//    private List<SdwnNodeEntity> nodes = new ArrayList<>();

    protected Date created;
    protected Date updated;

    public SwitchingNetwork()
    {
    }

    public SwitchingNetwork(String url)
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

    @Column(name = "url",nullable = false,unique = true)
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "controller_id", nullable = false)
    public SdwnControllerEntity getSdwnController()
    {
        return sdwnController;
    }

    public void setSdwnController(
            SdwnControllerEntity sdwnController)
    {
        this.sdwnController = sdwnController;
    }

//    @Fetch(FetchMode.SUBSELECT)
//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    public List<SdwnNodeEntity> getNodes()
//    {
//        return nodes;
//    }
//
//    public void setNodes(List<SdwnNodeEntity> nodes)
//    {
//        this.nodes = nodes;
//    }

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

    @Override
    public String toString()
    {
        return "Switching Network: id: " + getId()+ " url: " + getUrl()
                +" associated controller id: "+ getSdwnController().getId()
                + " with url: " + getSdwnController().getUrl();
    }
}
