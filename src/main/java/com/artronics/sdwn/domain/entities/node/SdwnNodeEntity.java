package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nodes")
public class SdwnNodeEntity implements Node
{
    protected Long id;

    protected Long address;

    private DeviceConnectionEntity device;

    //Normal as default value
    private Type type = Type.NORMAL;
    private Status status = Status.ACTIVE;
    private int battery;

    protected Date created;
    protected Date updated;

    public SdwnNodeEntity()
    {
    }

    public SdwnNodeEntity(Long address)
    {
        this.address = address;
    }

    public SdwnNodeEntity(Long address, DeviceConnectionEntity device)
    {
        this.address = address;
        this.device = device;
    }

    @Override
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

    @Column(name = "address",nullable = false,unique = false)
    public Long getAddress()
    {
        return address;
    }

    public void setAddress(Long address)
    {
        this.address = address;
    }


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", nullable = false)
    public DeviceConnectionEntity getDevice()
    {
        return device;
    }

    public void setDevice(DeviceConnectionEntity device)
    {
        this.device = device;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @Column(name = "battery")
    public int getBattery()
    {
        return battery;
    }

    public void setBattery(int battery)
    {
        this.battery = battery;
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

    public enum Type
    {
        SINK,
        NORMAL
    }

    public enum Status
    {
        ACTIVE,
        DISABLE,
    }

    @Override
    public int hashCode()
    {
        //use getters for getting fields(for ORM) see this SO answer:
        //http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
        Long nodeId = getId();

        if (nodeId == null) {
            throw new NullPointerException("Node id is null. Make sure node is persisted or fetch from database");
        }

        int result =17;

        int nodeIdInt = (int) (nodeId ^ (nodeId >>> 32));

        result+=nodeIdInt;

        return 31*result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SdwnNodeEntity))
            return false;
        if (obj == this)
            return true;

        SdwnNodeEntity rhs = (SdwnNodeEntity) obj;

        return this.getId().equals(rhs.getId());
    }

    @Override
    public String toString()
    {
        return "Node: "+address.toString();
    }

}
