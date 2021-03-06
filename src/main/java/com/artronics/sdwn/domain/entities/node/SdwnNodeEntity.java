package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.helpers.PrintHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "nodes")
public class SdwnNodeEntity implements Node,Serializable
{
    protected Long id;

    protected Long address;

    protected NetworkSession session;

    protected DeviceConnectionEntity device;

    //Normal as default value
    protected Type type = Type.NORMAL;
    protected Status status = Status.UNREGISTERED;

    protected Date created;
    protected Date updated;

    public static SdwnNodeEntity create(Long address,DeviceConnectionEntity device){
        SdwnNodeEntity n = new SdwnNodeEntity(address,device);

        return n;
    }
    public static SdwnNodeEntity create(Long address){
        SdwnNodeEntity n = new SdwnNodeEntity();
        n.setAddress(address);

        return n;
    }

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
    @Column(name = "id", nullable = false, unique = true)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "address", nullable = false, unique = false,updatable = false)
    public Long getAddress()
    {
        return address;
    }

    public void setAddress(Long address)
    {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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
        UNREGISTERED,
        IDLE,
        ACTIVE,
        DISABLE, ISLAND,
    }

    @Override
    public int hashCode()
    {
        //use getters for getting fields(for ORM) see this SO answer:
        //http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when
        // -overriding-equals-and-hashcode-in-java

        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.device);
        hcb.append(this.address);

        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SdwnNodeEntity))
            return false;
        if (obj == this)
            return true;

        SdwnNodeEntity that = (SdwnNodeEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();

        eb.append(this.device,that.device);
        eb.append(this.address,that.address);

        return eb.isEquals();
    }

    @Override
    public String toString()
    {
        return PrintHelper.printLongNode(this);
    }

}
