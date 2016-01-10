package com.artronics.sdwn.domain.entities.packet;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "packets")
public class PacketEntity implements Packet, Serializable
{
    private Long id;

    private DeviceConnectionEntity device;

    private List<Integer> content;

    private List<Integer> payload;

    private Packet.Direction dir;

    private int len;
    private int netId;

    private int srcShortAdd;
    private int dstShortAdd;

    private Packet.Type type;

    private int nextHop;

    public PacketEntity()
    {
    }

    public static PacketEntity create(List<Integer> content)
    {
        PacketEntity packet = new PacketEntity();

        packet.content = content;

        packet.payload = SdwnPacketHelper.getPayload(content);

        packet.dir = Packet.Direction.RX;

        packet.len = SdwnPacketHelper.getSize(content);
        packet.netId = SdwnPacketHelper.getNetId(content);

        packet.srcShortAdd = SdwnPacketHelper.getSourceAddress(content);
        packet.dstShortAdd = SdwnPacketHelper.getDestinationAddress(content);

        packet.type = SdwnPacketHelper.getType(content);

        packet.nextHop = SdwnPacketHelper.getNextHopAddress(content);

        return packet;
    }

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

    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionType(type = "java.util.ArrayList")
    @CollectionTable(name = "packet_payload", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "payload")
    public List<Integer> getPayload()
    {
        return payload;
    }

    public void setPayload(List<Integer> payload)
    {
        this.payload = payload;
    }

    @Enumerated(EnumType.STRING)
    public Direction getDir()
    {
        return dir;
    }

    public void setDir(Direction dir)
    {
        this.dir = dir;
    }

    public int getLen()
    {
        return len;
    }

    public void setLen(int len)
    {
        this.len = len;
    }

    public int getNetId()
    {
        return netId;
    }

    public void setNetId(int netId)
    {
        this.netId = netId;
    }

    public int getSrcShortAdd()
    {
        return srcShortAdd;
    }

    public void setSrcShortAdd(int srcShortAdd)
    {
        this.srcShortAdd = srcShortAdd;
    }

    public int getDstShortAdd()
    {
        return dstShortAdd;
    }

    public void setDstShortAdd(int dstShortAdd)
    {
        this.dstShortAdd = dstShortAdd;
    }

    @Enumerated(EnumType.STRING)
    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public int getNextHop()
    {
        return nextHop;
    }

    public void setNextHop(int nextHop)
    {
        this.nextHop = nextHop;
    }

    @Override
    @Transient
    public List<Integer> getContent()
    {
        return content;
    }
}
