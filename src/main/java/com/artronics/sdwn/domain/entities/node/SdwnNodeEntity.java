package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nodes")
public class SdwnNodeEntity implements Node,Serializable
{
    protected Long id;

    protected Long address;

    protected NetworkSession session;

    protected DeviceConnectionEntity device;

    protected Set<SdwnNeighbor> neighbors = new HashSet<>();

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "node")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations
            .CascadeType.PERSIST})
    public Set<SdwnNeighbor> getNeighbors()
    {
        return neighbors;
    }

    public void setNeighbors(
            Set<SdwnNeighbor> neighbors)
    {
        this.neighbors = neighbors;
    }

//    public void addNeighbor(SdwnNeighbor neighbor){
//        this.neighbors.add(neighbor);
//    }

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
        IDLE,
        ACTIVE,
        DISABLE,
    }

    @Override
    public int hashCode()
    {
        //use getters for getting fields(for ORM) see this SO answer:
        //http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when
        // -overriding-equals-and-hashcode-in-java

//        Long id_l = getDevice().getId() == null ? 0 : getDevice().getId();
//        Long add_l = getAddress();
//
//        int add = (int) (add_l ^ (add_l >>> 32));
//        int id = (int) (id_l ^ (id_l >>> 32));
//
//        final int prime = 31;
//
//        int result = 1;
//
//        result = prime * result + add;
//        result = prime * result + id;
//
//        return result;

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
        String node = this.getType() == Type.NORMAL ? "Node: " : "Sink: ";
        return String.format(node + "%-5d", this.getAddress());
    }

}
