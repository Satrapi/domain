package com.artronics.sdwn.domain.helpers;


import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.entities.packet.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.artronics.sdwn.domain.entities.packet.Packet.Type.*;

/**
 * It creates a fake SdwnPacket of all types The default convention for non-parameter methods is as
 * follow: src is always 30, dst is always 0, payload for data is from 0 to payload length and for
 * default length is 10,
 */
public class FakePacketFactory
{
    public static final Double WEIGHT = 113D;
    List<Integer> packet = new ArrayList<>();
    List<Integer> header = new ArrayList<>();

    PacketFactory packetFactory = new SdwnPacketFactory();

    private List<Integer> createHeader()
    {
        return createHeader(10, DATA, 30, 0);
    }

    private List<Integer> createHeader(int len, Packet.Type type, int src, int dst)
    {
        Integer[] bytes = {
                len,//length
                1, //NetId
                SdwnPacketHelper.getHighAddress(src),
                SdwnPacketHelper.getLowAddress(src),

                SdwnPacketHelper.getHighAddress(dst),
                SdwnPacketHelper.getLowAddress(dst),
                type.getValue(),//type
                20, //MAX_TTL
                0,//next hop H
                0,//next hop L
        };
        header = Arrays.asList(bytes);

        return new ArrayList<>(header);
    }

    public PacketEntity createReportPacket()
    {
        return PacketEntity.create(createRawReportPacket(),null);
    }

    public SdwnReportPacket createReportPacket(Long packetId,SdwnNodeEntity src, SdwnNodeEntity dst,
                                               DeviceConnectionEntity device,
                                               Long... add){

        SdwnReportPacket packet = new SdwnReportPacket();
        setSrcDstNodes(src, dst, REPORT,packet,packetId,device);

        List<SdwnNeighbor> ns= new ArrayList<>();
        for (Long a:add){
            SdwnNodeEntity n = new SdwnNodeEntity(a,device);
            n.setId(a);
            SdwnNeighbor ni = new SdwnNeighbor(n,WEIGHT,255-WEIGHT.intValue());
            ns.add(ni);
        }
        packet.setNeighbors(ns);

        return packet;
    }
    public SdwnReportPacket createReportPacket(Long packetId,SdwnNodeEntity src, SdwnNodeEntity dst,
                                               DeviceConnectionEntity device,
                                               SdwnNodeEntity... nodes){

        SdwnReportPacket packet = new SdwnReportPacket();
        setSrcDstNodes(src, dst, REPORT,packet,packetId,device);

        List<SdwnNeighbor> ns= new ArrayList<>();
        for (SdwnNodeEntity node:nodes){
            node.setDevice(device);
            node.setId(node.getAddress());
            SdwnNeighbor ni = new SdwnNeighbor(node,WEIGHT,255-WEIGHT.intValue());
            ns.add(ni);
        }
        packet.setNeighbors(ns);

        return packet;
    }

    public PacketEntity setSrcDstNodes(SdwnNodeEntity src,SdwnNodeEntity dst, Packet.Type type,
                                       PacketEntity packet,Long packetId,DeviceConnectionEntity device){
        packet.setId(packetId);
        packet.setType(type);

        src.setDevice(device);
        src.setId(src.getAddress());

        dst.setDevice(device);
        dst.setId(dst.getAddress());

        packet.setSrcNode(src);
        packet.setDstNode(dst);

        return packet;
    }

    public PacketEntity createReportPacket(int src, int dst, int dis, int bat, List<Integer> neighbors)
    {
        return PacketEntity.create(createRawReportPacket(src, dst, dis, bat, neighbors),null);
    }

    public PacketEntity createReportPacket(int src, int dst, List<Integer> neighbors)
    {
        return createReportPacket(src, dst, 1, 255, neighbors);
    }

    public List<Integer> createRawReportPacket(int src, int dst, List<Integer> neighbors)
    {
        return createRawReportPacket(src, dst, 1, 255, neighbors);
    }

    public List<Integer> createRawReportPacket(int src, int dst, int dis, int bat,
                                               List<Integer> neighbors)
    {
        List<Integer> header = createHeader(Packet.HEADER_LEN + 3 + neighbors.size(),
                                            REPORT,
                                            src, dst);

        List<Integer> extra = new ArrayList<>();
        extra.add(dis);//distance
        extra.add(bat);//battery
        extra.add(neighbors.size() / 3);//number of neighbors

        packet = new ArrayList<>(header);
        packet.addAll(extra);
        packet.addAll(neighbors);

        return packet;
    }

    public List<Integer> createRawReportPacket()
    {
        List<Integer> neighbors = createNeighbors(35, 36, 37);

        return createRawReportPacket(30, 0, 1, 255, neighbors);
    }

    public List<Integer> createNeighbors(int... addrs)
    {
        List<Integer> neighbors = new ArrayList<>();
        Random rn = new Random();

        for (int addr : addrs) {
            neighbors.add(SdwnPacketHelper.getHighAddress(addr));
            neighbors.add(SdwnPacketHelper.getLowAddress(addr));
            neighbors.add(rn.nextInt(255)); //random rssi
        }

        return neighbors;
    }

    public Packet createDataPacket(int src, int dst)
    {
        return PacketEntity.create(createRawDataPacket(src, dst, 10),null);
    }

    public Packet createDataPacket()
    {
        return PacketEntity.create(createRawDataPacket(30, 0, 10),null);

    }

    public List<Integer> createRawDataPacket(int src, int dst)
    {
        return createRawDataPacket(src, dst, 10);
    }

    public List<Integer> createRawDataPacket(int src, int dst, int payloadLen)
    {
        List<Integer> header = createHeader(Packet.HEADER_LEN + payloadLen,
                                            DATA, src, dst);

        List<Integer> packet = new ArrayList<>(header);
        for (int i = 0; i < payloadLen; i++) {
            packet.add(i);
        }

        return packet;
    }

    public List<Integer> createRawDataPacket()
    {
        return createRawDataPacket(30, 0, 10);
    }

    public Packet createRuleRequestPacket(int src, int dst, int echoPayloadLen)
    {
        return PacketEntity.create(createRawRuleRequestPacket(src, dst, echoPayloadLen),null);
    }

    public List<Integer> createRawRuleRequestPacket(int src, int dst, int echoPayloadLen)
    {
        List<Integer> packet = createHeader(10 + echoPayloadLen,
                                            RL_REQ,
                                            src,
                                            dst);
        List<Integer> payload = new ArrayList<>();

        for (int i = 0; i < echoPayloadLen; i++) {
            payload.add(i);
        }

        packet.addAll(payload);

        return packet;
    }

    public List<Integer> createRawOpenPathPacket(int src, int dst, List<Integer> path)
    {
        List<Integer> packet = createHeader(10 + path.size() * 2,
                                            OPN_PT,
                                            src,
                                            dst);
        List<Integer> payload = new ArrayList<>();

        for (int i = 0; i < path.size(); i++) {
            payload.add(i * 2, SdwnPacketHelper.getHighAddress(path.get(i)));
            payload.add(i * 2 + 1, SdwnPacketHelper.getLowAddress(path.get(i)));
        }

        packet.addAll(payload);

        return packet;
    }
}
