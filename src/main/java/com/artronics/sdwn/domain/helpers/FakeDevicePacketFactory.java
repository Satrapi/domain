package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.entities.packet.Packet;
import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FakeDevicePacketFactory
{
    public static final Double WEIGHT = 113D;

    private final static Logger log = Logger.getLogger(FakeDevicePacketFactory.class);

    public SdwnReportPacket createReportPacket(Long packetId, SdwnNodeEntity src,
                                               SdwnNodeEntity dst, Long... add)
    {
        SdwnReportPacket packet = new SdwnReportPacket();

        setSrcDstNodes(src, dst,packet, Packet.Type.REPORT,packetId);

        packet.setNeighbors(createDeviceNeighbors(add));

        return packet;
    }

    public SdwnReportPacket createReportPacket(Long packetId, SdwnNodeEntity src,
                                               SdwnNodeEntity dst, SdwnNodeEntity... nodes)
    {
        SdwnReportPacket packet = new SdwnReportPacket();

        setSrcDstNodes(src, dst,packet, Packet.Type.REPORT,packetId);

        packet.setNeighbors(createDeviceNeighbors(nodes));

        return packet;
    }

    public static List<SdwnNeighbor> createDeviceNeighbors(Long... add){
        List<SdwnNeighbor> neighbors= new ArrayList<>();
        for (Long a : add) {
            SdwnNodeEntity node = new SdwnNodeEntity(a);
            SdwnNeighbor ni = new SdwnNeighbor(node, WEIGHT, 255-WEIGHT.intValue());
            neighbors.add(ni);
        }

        return neighbors;
    }

    public static List<SdwnNeighbor> createDeviceNeighbors(SdwnNodeEntity... nodes){
        List<SdwnNeighbor> neighbors= new ArrayList<>();
        for (SdwnNodeEntity node : nodes) {
            SdwnNeighbor ni = new SdwnNeighbor(node, WEIGHT, 255-WEIGHT.intValue());
            neighbors.add(ni);
        }

        return neighbors;
    }
    public PacketEntity setSrcDstNodes(SdwnNodeEntity src, SdwnNodeEntity dst,
                                       PacketEntity packet, Packet.Type type,Long packetId){
        packet.setId(packetId);
        packet.setType(type);
        packet.setSrcNode(src);
        packet.setDstNode(dst);

        return packet;
    }
}
