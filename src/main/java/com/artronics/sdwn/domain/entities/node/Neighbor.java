package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.Packet;
import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;

import java.util.ArrayList;
import java.util.List;

public class Neighbor extends SdwnNode
{
    public final static int NEIGHBOR_INDEX=13;

    private final int rssi;

    public Neighbor(Long address, int rssi)
    {
        super(address);
        this.rssi = rssi;
    }

    public static List<Neighbor> createNeighbors(Packet packet)
    {
        List<Neighbor> neighbors = new ArrayList<>();
        List<Integer> contents = packet.getContent();

        for (int i = NEIGHBOR_INDEX; i < contents.size(); i += 3) {
            int add = SdwnPacketHelper.joinAddresses(contents.get(i),
                                                     contents.get(i + 1));
            int rssi = contents.get(i + 2);
            Neighbor neighbor = new Neighbor(Integer.toUnsignedLong(add), rssi);
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
    public int getRssi()
    {
        return rssi;
    }
}
