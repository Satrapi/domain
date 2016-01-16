package com.artronics.sdwn.domain.entities;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "devices")
public class DeviceConnectionEntity implements Serializable
{
    private  Long id;

    private String url;

    private SdwnNodeEntity sinkNode;

    private String description;

    private SdwnControllerEntity sdwnController;

    protected Date created;
    protected Date updated;

    public DeviceConnectionEntity()
    {
    }

    public DeviceConnectionEntity(String url)
    {
        this.url = url;
    }

    public DeviceConnectionEntity(String url, SdwnNodeEntity sinkNode)
    {
        this.url = url;
        this.sinkNode = sinkNode;
    }

    public DeviceConnectionEntity(Long id, String url, SdwnNodeEntity sinkNode)
    {
        this.id = id;
        this.url = url;
        this.sinkNode = sinkNode;
    }
    public DeviceConnectionEntity(Long id){
        this.id = id;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sink_node_id",nullable = true)
    public SdwnNodeEntity getSinkNode()
    {
        return sinkNode;
    }

    public void setSinkNode(SdwnNodeEntity sinkNode)
    {
        this.sinkNode = sinkNode;
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
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
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        DeviceConnectionEntity that = (DeviceConnectionEntity) o;

        return new EqualsBuilder()
                .append(getUrl(), that.getUrl())
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(getUrl())
                .toHashCode();
    }

    @Override
    public String toString()
    {
        String s = "DeviceConnection-> URL: "+ getUrl();
        s+=getId()==null? "": " ID: " +getId();

        return s;
    }
}
