package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class NodeRepoTest extends BaseRepoTest
{
    @Override
    @Before
    public void setUp() throws Exception
    {
        super.setUp();

    }

    @Test
    @Transactional
    public void test_save(){
        SdwnNodeEntity node = new SdwnNodeEntity(SINK_ADD);
        node.setDevice(device);

        nodeRepo.save(node);

        assertNotNull(node.getId());
    }

    @Test
    @Transactional
    public void test_create(){
        SdwnNodeEntity node = new SdwnNodeEntity(34L);

        nodeRepo.create(node,device.getId());

        assertNotNull(node.getId());
    }

    @Test
    public void test_persistNeighbors(){
        SdwnNodeEntity node = persistNode(new SdwnNodeEntity(10L));
        Neighbor n0 = new Neighbor(20L,123,device);
        Neighbor n1 = new Neighbor(21L,212,device);
        Set<Neighbor> neighbors = new HashSet<>();
        neighbors.add(n0);
        neighbors.add(n1);

        nodeRepo.persistNeighbors(node,neighbors);

        neighbors = neighborRepo.fetchNeighbors(node);

        Iterator<Neighbor> it = neighbors.iterator();
        while (it.hasNext()){
            Neighbor n =it.next();
            assertNotNull(n.getId());
        }
    }
    private SdwnNodeEntity persistNode(SdwnNodeEntity node){
        nodeRepo.create(node,device.getId());

        return node;
    }
}