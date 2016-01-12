package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SdwnNeighbor implements Neighbor<SdwnNodeEntity>
{
    public final static int NEIGHBOR_INDEX=13;

    private SdwnNodeEntity node;
    private Integer rssi;

    private Double weight;

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

    public void setNode(SdwnNodeEntity node)
    {
        this.node = node;
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

    //    @Override
//    public int hashCode()
//    {
//        int result = super.hashCode();
//        final int prime = 31;
//        //use getters for getting fields(for ORM) see this SO answer:
//        //http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
//
//        result = prime * result + getWeight();
//
//        return result;
//    }

//    @Override
//    public boolean equals(Object obj)
//    {
//        if (!(obj instanceof SdwnNeighbor))
//            return false;
//        if (obj == this)
//            return true;
//        if(obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//
//        SdwnNeighbor rhs = (SdwnNeighbor) obj;
//
//        return this.getAddress().equals(rhs.getAddress())&&
//                this.getDevice().getId().equals(rhs.getDevice().getId())&&
//                this.getWeight().equals(rhs.getWeight());
//    }
}
