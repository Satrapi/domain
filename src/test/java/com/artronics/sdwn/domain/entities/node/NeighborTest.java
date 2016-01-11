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
    List<Neighbor> neighbors;

    @Before
    public void setUp() throws Exception
    {
        packetBytes = factory.createRawReportPacket();
        packet = PacketEntity.create(packetBytes);
        packet.setDevice(device);

        neighbors = new ArrayList<>(Neighbor.createNeighbors(packet));

        Long id =0L;
        for (Neighbor neighbor : neighbors) {
            neighbor.setId(id);
            neighbor.setDevice(device);
            id++;
        }
    }

    @Test
    public void it_should_create_neighbors_by_passing_reportPacket()
    {
        assertTrue(neighbors.contains(new Neighbor(35L,device)));
        assertTrue(neighbors.contains(new Neighbor(36L,device)));
        assertTrue(neighbors.contains(new Neighbor(37L,device)));
    }
    
    @Test
    public void test_equals(){
        Neighbor n0 = new Neighbor(20L,30,device);
        assertTrue(n0.equals(n0));

        Neighbor sameN0 = new Neighbor(20L,30,device);
        assertTrue(n0.equals(sameN0));
        assertTrue(sameN0.equals(n0));

        Neighbor n1 = new Neighbor(20L,50,device);
        assertTrue(n0.equals(n1));
        assertFalse(n0.equals(new Neighbor(21L,30,device)));
        assertFalse(n0.equals(new Neighbor(20L,30,new DeviceConnectionEntity(31L,"foo",43L))));
    }

    @Test
    public void test_hashCode(){
        //if two are equals hashcode must be equal to
        Neighbor n0 = new Neighbor(20L,30,device);
        Neighbor sameN0 = new Neighbor(20L,30,device);

        assertThat(n0.hashCode(),equalTo(sameN0.hashCode()));

        Neighbor n1 = new Neighbor(20L,50,device);
        assertThat(n0.hashCode(),equalTo(n1.hashCode()));
    }

    @Test
    public void contains_method_in_set_should_check_for_nodeId_and_deviceId(){
        Neighbor n0 = new Neighbor(20L,30,device);
        Neighbor n1 = new Neighbor(20L,50,device);
        Set<Neighbor> n = new HashSet<>();
        n.add(n1);

        assertTrue(n.contains(n0));
    }

}