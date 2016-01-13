package com.artronics.sdwn.domain.entities.packet;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "packets")
public class PacketEntity implements Packet, Serializable
{
    private Long id;

    private DeviceConnectionEntity device;

    private NetworkSession session;

    private SdwnNodeEntity srcNode;

    private SdwnNodeEntity dstNode;

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

    public static PacketEntity create(List<Integer> content,DeviceConnectionEntity device)
    {
        PacketEntity packet = new PacketEntity();
        packet.setDevice(device);

        packet.srcShortAdd = SdwnPacketHelper.getSourceAddress(content);
        packet.dstShortAdd = SdwnPacketHelper.getDestinationAddress(content);

        SdwnNodeEntity src = new SdwnNodeEntity(Integer.toUnsignedLong(packet.srcShortAdd),device);
        SdwnNodeEntity dst =  new SdwnNodeEntity(Integer.toUnsignedLong(packet.dstShortAdd),device);
        packet.srcNode = src;
        packet.dstNode = dst;

        packet.content = content;

        packet.payload = SdwnPacketHelper.getPayload(content);

        packet.dir = Packet.Direction.RX;

        packet.len = SdwnPacketHelper.getSize(content);
        packet.netId = SdwnPacketHelper.getNetId(content);


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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "src_node_id",nullable = false)
    public SdwnNodeEntity getSrcNode()
    {
        return srcNode;
    }

    public void setSrcNode(SdwnNodeEntity srcNode)
    {
        this.srcNode = srcNode;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dst_node_id",nullable = false)
    public SdwnNodeEntity getDstNode()
    {
        return dstNode;
    }

    public void setDstNode(SdwnNodeEntity dstNode)
    {
        this.dstNode = dstNode;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id", nullable = false)
    public DeviceConnectionEntity getDevice()
    {
        return device;
    }

    public void setDevice(DeviceConnectionEntity device)
    {
        this.device = device;
    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "session_id",nullable = false)
    public NetworkSession getSession()
    {
        return session;
    }

    public void setSession(NetworkSession session)
    {
        this.session = session;
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
