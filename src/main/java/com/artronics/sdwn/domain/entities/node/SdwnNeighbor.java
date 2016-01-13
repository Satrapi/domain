package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SdwnNeighbor implements Neighbor<SdwnNodeEntity>
{
    public final static int NEIGHBOR_INDEX=13;

    private final SdwnNodeEntity node;
    private Integer rssi;

    private Double weight;

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

    @Override
    public SdwnNodeEntity getNode()
    {
        return node;
    }

    @Override
    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

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
