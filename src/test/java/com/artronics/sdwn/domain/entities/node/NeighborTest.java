package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.helpers.FakePacketFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NeighborTest
{

    FakePacketFactory factory = new FakePacketFactory();
    List<Integer> packetBytes;
    PacketEntity packet;
    List<Neighbor> neighbors;

    @Before
    public void setUp() throws Exception
    {
        packetBytes = factory.createRawReportPacket();
        packet = PacketEntity.create(packetBytes);
        neighbors = Neighbor.createNeighbors(packet);
    }

    @Test
    public void it_should_create_neighbors_by_passing_reportPacket()
    {

        assertThat(neighbors.size(), equalTo(3));
        assertThat(neighbors.get(0).getAddress(), equalTo(35L));
        assertThat(neighbors.get(1).getAddress(), equalTo(36L));
        assertThat(neighbors.get(2).getAddress(), equalTo(37L));
    }

    @Test
    public void test_up_casting_to_node()
    {
        SdwnNodeEntity node = neighbors.get(0);
        SdwnNodeEntity sameNode = new SdwnNodeEntity(35L);

        assertEquals(sameNode, node);
    }

    @Test
    public void it_should_be_equal_to_a_Node_just_if_addresses_are_the_same(){
        SdwnNodeEntity node = new SdwnNodeEntity(1L);
        Neighbor neighbor = new Neighbor(1L,23);

        assertThat(node,equalTo(neighbor));
    }

    @Test
    public void it_should_work_in_HashSet_contains_methods(){
        //we add a node and a neighbor with same address
        //HashSet must be contains one of them as soon as
        //generic type is Node
        SdwnNodeEntity node0= new SdwnNodeEntity(0L);
        Neighbor neighbor0 = new Neighbor(0L,32);
        Set<Node> set = new HashSet<>();
        set.add(node0);
        set.add(neighbor0);

        assertThat(set.size(),equalTo(1));
        assertTrue(set.contains(neighbor0));
        assertTrue(set.contains(node0));
    }

}