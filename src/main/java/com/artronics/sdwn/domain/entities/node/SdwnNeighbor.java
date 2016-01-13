package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "neighbor")
public class SdwnNeighbor implements Neighbor<SdwnNodeEntity>
{
    public final static int NEIGHBOR_INDEX=13;

    private Long id;

    private SdwnNodeEntity node;
    private Integer rssi;

    private Double weight;

    public SdwnNeighbor()
    {
    }

    public SdwnNeighbor(SdwnNodeEntity node, Double weight)
    {
        this.node = node;
        this.weight = weight;
    }

    public SdwnNeighbor(SdwnNodeEntity node, Integer rssi)
    {
        this.node = node;
        this.rssi = rssi;
    }

    public static Set<SdwnNeighbor> createNeighbors(PacketEntity packet)
    {
        Set<SdwnNeighbor> neighbors = new HashSet<>();
        List<Integer> contents = packet.getContent();

        for (int i = NEIGHBOR_INDEX; i < contents.size(); i += 3) {
            int add = SdwnPacketHelper.joinAddresses(contents.get(i),
                                                     contents.get(i + 1));
            int rssi = contents.get(i + 2);
            SdwnNodeEntity node = new SdwnNodeEntity(Integer.toUnsignedLong(add));
            SdwnNeighbor neighbor = new SdwnNeighbor(node,rssi);
            neighbors.add(neighbor);
        }

        return neighbors;
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
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "node_id")
    public SdwnNodeEntity getNode()
    {
        return node;
    }

    public void setNode(SdwnNodeEntity node)
    {
        this.node = node;
    }

    @Override
    @Column(name = "weight",nullable = false,unique = false)
    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    @Column(name = "rssi",nullable = false,unique = false)
    public Integer getRssi()
    {
        return rssi;
    }

    public void setRssi(Integer rssi)
    {
        this.rssi = rssi;
    }

        @Override
    public int hashCode()
    {
        return this.node.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SdwnNeighbor))
            return false;
        if (obj == this)
            return true;
        if(obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        SdwnNeighbor n=(SdwnNeighbor) obj;
        return this.node.equals(n.getNode());
    }
}
