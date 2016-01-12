package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nodes")
public class SdwnNodeEntity implements Node
{
    protected Long id;

    protected Long address;

    protected NetworkSession session;

    protected DeviceConnectionEntity device;

    //Normal as default value
    protected Type type = Type.NORMAL;
    protected Status status = Status.ACTIVE;
    protected int battery;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "session_id", nullable = false)
    public NetworkSession getSession()
    {
        return session;
    }

    public void setSession(NetworkSession session)
    {
        this.session = session;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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

        Long id_l = getDevice().getId() ==null ? 0 : getDevice().getId();
        Long add_l = getAddress();

        int add = (int) (add_l ^ (add_l >>>32));
        int id = (int) (id_l ^ (id_l>>>32));

        final int prime = 31;

        int result = 1;

        result = prime * result +add;
        result = prime * result + id;

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SdwnNodeEntity))
            return false;
        if (obj == this)
            return true;
        if(obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        SdwnNodeEntity rhs = (SdwnNodeEntity) obj;

        return this.getAddress().equals(rhs.getAddress())&&
                this.getDevice().getId().equals(rhs.getDevice().getId());
    }

    @Override
    public String toString()
    {
        return "Node: "+address.toString();
    }

}
