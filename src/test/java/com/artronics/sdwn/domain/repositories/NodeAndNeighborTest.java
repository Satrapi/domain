package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NodeAndNeighborTest extends BaseRepoTest
{
    private final static Logger log = Logger.getLogger(NodeAndNeighborTest.class);

    @Test
    public void it_should_persist_neighbors_attached_to_node(){
        SdwnNodeEntity node = persistNeighbors(20L,21L,222);
        Set<Neighbor> neighbors = node.getNeighbors();

        for (Neighbor neighbor : neighbors) {
            assertNotNull(neighbor.getId());
        }
    }

    @Test
    public void it_should_update_current_neighbor_if_rssi_changed(){
        SdwnNodeEntity node = persistNeighbors(20L,21L,222);
        Neighbor ne = createNeighbor(21L,233);
        node.addNeighbor(ne);

        neighborRepo.save(ne);

        nodeRepo.save(node);
        Set<Neighbor> neighbors = node.getNeighbors();
        assertThat(neighbors.size(),equalTo(1));

        for (Neighbor neighbor : neighbors) {
            assertNotNull(neighbor.getId());
            assertThat(neighbor.getRssi(),equalTo(233));
        }

    }
    private SdwnNodeEntity persistNeighbors(Long srcNodeAdd,Long neiAdd,int rssi){
        SdwnNodeEntity node = createNode(srcNodeAdd);
        Neighbor n = createNeighbor(neiAdd,rssi);
        node.addNeighbor(n);
        nodeRepo.save(node);

        return node;
    }
    private Neighbor createNeighbor(Long add,int rssi){
        Neighbor n = new Neighbor(add,rssi,device);
        n.setSession(session);

        return n;
    }
    private SdwnNodeEntity createNode(Long add){
        SdwnNodeEntity n = new SdwnNodeEntity(add,device);
        n.setSession(session);

        return n;
    }
}
