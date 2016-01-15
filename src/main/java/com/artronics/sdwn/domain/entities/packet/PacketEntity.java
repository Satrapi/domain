package com.artronics.sdwn.domain.entities.packet;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "packets")
@Inheritance(strategy = InheritanceType.JOINED)
public class PacketEntity implements Packet, Serializable
{
    private Long id;

    private NetworkSession session;

    private SdwnNodeEntity srcNode;

    private SdwnNodeEntity dstNode;

    private List<Integer> content;

    private Packet.Type type;

    private Packet.Direction dir;

    private int len;

    private int netId;

    private int nextHop;

    public PacketEntity()
    {
    }

    public PacketEntity(List<Integer> content)
    {
        this.content = content;

        Long srcShortAdd =Integer.toUnsignedLong(SdwnPacketHelper.getSourceAddress(content));
        Long dstShortAdd =Integer.toUnsignedLong(SdwnPacketHelper.getDestinationAddress(content));

        SdwnNodeEntity src =  SdwnNodeEntity.create(srcShortAdd);
        SdwnNodeEntity dst =  SdwnNodeEntity.create(dstShortAdd);
        this.srcNode = src;
        this.dstNode = dst;

        this.type = SdwnPacketHelper.getType(content);

        this.dir = Packet.Direction.RX;

        this.len = SdwnPacketHelper.getSize(content);
        this.netId = SdwnPacketHelper.getNetId(content);
        this.nextHop = SdwnPacketHelper.getNextHopAddress(content);
    }

    public static PacketEntity create(List<Integer> content, DeviceConnectionEntity device)
    {
        PacketEntity packet = new PacketEntity();

        packet.content = content;

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

    @Override
    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionType(type = "java.util.ArrayList")
    @CollectionTable(name = "packet_content", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "content")
    public List<Integer> getContent()
    {
        return content;
    }

    public void setContent(List<Integer> content)
    {
        this.content = content;
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
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public SdwnNodeEntity getDstNode()
    {
        return dstNode;
    }

    public void setDstNode(SdwnNodeEntity dstNode)
    {
        this.dstNode = dstNode;
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

}
