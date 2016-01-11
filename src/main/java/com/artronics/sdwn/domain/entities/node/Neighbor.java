package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "neighbors")
public class Neighbor extends SdwnNodeEntity
{
    public final static int NEIGHBOR_INDEX=13;

    private Integer rssi;

    private SdwnNodeEntity parentNode;

    public Neighbor(Long address, int rssi)
    {
        super(address);
        this.rssi = rssi;
    }

    public static List<Neighbor> createNeighbors(PacketEntity packet)
    {
        List<Neighbor> neighbors = new ArrayList<>();
        List<Integer> contents = packet.getContent();

        for (int i = NEIGHBOR_INDEX; i < contents.size(); i += 3) {
            int add = SdwnPacketHelper.joinAddresses(contents.get(i),
                                                     contents.get(i + 1));
            int rssi = contents.get(i + 2);
            Neighbor neighbor = new Neighbor(Integer.toUnsignedLong(add), rssi);
            neighbor.setDevice(packet.getDevice());
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    @Override
    public String toString()
    {
        String s="Neighbor: ";

        s+=getId()==null ? "": "id: " +getId();
        s+="add: " +getAddress();

        return s;
    }

    @Column(name = "rssi",nullable = false,unique = false)
    public int getRssi()
    {
        return rssi;
    }

    public void setRssi(Integer rssi)
    {
        this.rssi = rssi;
    }

}
