package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.helpers.FakePacketFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NeighborTest
{

    FakePacketFactory factory = new FakePacketFactory();
    DeviceConnectionEntity device = new DeviceConnectionEntity(10L,"foo",0L);
    List<Integer> packetBytes;
    PacketEntity packet;
    List<SdwnNeighbor> neighbors;

    @Before
    public void setUp() throws Exception
    {
        packetBytes = factory.createRawReportPacket();
        packet = PacketEntity.create(packetBytes);
        packet.setDevice(device);

        neighbors = new ArrayList<>(SdwnNeighbor.createNeighbors(packet));

        Long id =0L;
        for (SdwnNeighbor neighbor : neighbors) {
            neighbor.setId(id);
            neighbor.setDevice(device);
            id++;
        }
    }

    @Test
    public void it_should_create_neighbors_by_passing_reportPacket()
    {
        assertTrue(neighbors.contains(new SdwnNeighbor(35L, device)));
        assertTrue(neighbors.contains(new SdwnNeighbor(36L, device)));
        assertTrue(neighbors.contains(new SdwnNeighbor(37L, device)));
    }
    
    @Test
    public void test_equals(){
        SdwnNeighbor n0 = new SdwnNeighbor(20L, 30, device);
        assertTrue(n0.equals(n0));

        SdwnNeighbor sameN0 = new SdwnNeighbor(20L, 30, device);
        assertTrue(n0.equals(sameN0));
        assertTrue(sameN0.equals(n0));

        SdwnNeighbor n1 = new SdwnNeighbor(20L, 50, device);
        assertTrue(n0.equals(n1));
        assertFalse(n0.equals(new SdwnNeighbor(21L, 30, device)));
        assertFalse(n0.equals(new SdwnNeighbor(20L, 30, new DeviceConnectionEntity(31L, "foo", 43L))));
    }

    @Test
    public void test_hashCode(){
        //if two are equals hashcode must be equal to
        SdwnNeighbor n0 = new SdwnNeighbor(20L, 30, device);
        SdwnNeighbor sameN0 = new SdwnNeighbor(20L, 30, device);

        assertThat(n0.hashCode(),equalTo(sameN0.hashCode()));

        SdwnNeighbor n1 = new SdwnNeighbor(20L, 50, device);
        assertThat(n0.hashCode(),equalTo(n1.hashCode()));
    }

    @Test
    public void contains_method_in_set_should_check_for_nodeId_and_deviceId(){
        SdwnNeighbor n0 = new SdwnNeighbor(20L, 30, device);
        SdwnNeighbor n1 = new SdwnNeighbor(20L, 50, device);
        Set<SdwnNeighbor> n = new HashSet<>();
        n.add(n1);

        assertTrue(n.contains(n0));
    }

}