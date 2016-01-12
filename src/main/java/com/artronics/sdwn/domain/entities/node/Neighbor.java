package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Neighbor extends SdwnNodeEntity
{
    public final static int NEIGHBOR_INDEX=13;

    private Integer rssi;

    public Neighbor()
    {
    }

    public Neighbor(Long address, int rssi, DeviceConnectionEntity device)
    {
        super(address);
        this.rssi = rssi;
        setDevice(device);
    }

    public Neighbor(Long address, DeviceConnectionEntity device)
    {
        super(address, device);
    }

    public static Set<Neighbor> createNeighbors(PacketEntity packet)
    {
        Set<Neighbor> neighbors = new HashSet<>();
        List<Integer> contents = packet.getContent();

        for (int i = NEIGHBOR_INDEX; i < contents.size(); i += 3) {
            int add = SdwnPacketHelper.joinAddresses(contents.get(i),
                                                     contents.get(i + 1));
            int rssi = contents.get(i + 2);
            Neighbor neighbor = new Neighbor(Integer.toUnsignedLong(add), rssi,packet.getDevice());
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    @Override
    public String toString()
    {
        String s="Neighbor-> ";

        s+=getId()==null ? "ID: null": "ID: " +getId();
        s+=" ADD: " +getAddress();

        return s;
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

//    @Override
//    public int hashCode()
//    {
//        int result = super.hashCode();
//        final int prime = 31;
//        //use getters for getting fields(for ORM) see this SO answer:
//        //http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
//
//        result = prime * result + getRssi();
//
//        return result;
//    }

//    @Override
//    public boolean equals(Object obj)
//    {
//        if (!(obj instanceof Neighbor))
//            return false;
//        if (obj == this)
//            return true;
//        if(obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//
//        Neighbor rhs = (Neighbor) obj;
//
//        return this.getAddress().equals(rhs.getAddress())&&
//                this.getDevice().getId().equals(rhs.getDevice().getId())&&
//                this.getRssi().equals(rhs.getRssi());
//    }
}
