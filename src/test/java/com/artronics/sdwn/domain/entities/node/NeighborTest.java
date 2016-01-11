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
    public void test_up_casting_to_node()
    {
        SdwnNodeEntity node = neighbors.get(0);
        SdwnNodeEntity sameNode = new SdwnNodeEntity(37L,device);

        assertEquals(sameNode, node);
    }

    @Test
    public void it_should_be_equal_to_a_Node_just_if_addresses_are_the_same(){
        SdwnNodeEntity node = new SdwnNodeEntity(1L,device);
        Neighbor neighbor = new Neighbor(1L,device);
        neighbor.setRssi(22);

        assertThat(node,equalTo(neighbor));
    }

    @Test
    public void it_should_work_in_HashSet_contains_methods(){
        //we add a node and a neighbor with same address
        //HashSet must be contains one of them as soon as
        //generic type is Node
        SdwnNodeEntity node0= new SdwnNodeEntity(0L,device);
        Neighbor neighbor0 = new Neighbor(0L,device);
        neighbor0.setRssi(32);

        Set<Node> set = new HashSet<>();
        set.add(node0);
        set.add(neighbor0);

        assertThat(set.size(),equalTo(1));
        assertTrue(set.contains(neighbor0));
        assertTrue(set.contains(node0));
    }

}