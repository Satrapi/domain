package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Ignore;
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

    //TODO change it to new neighbor model
    @Ignore("run it if you change neighbor model")
    @Test
    public void test_persistNeighbors(){
        SdwnNodeEntity node = persistNode(new SdwnNodeEntity(10L));
        SdwnNeighbor n0 = new SdwnNeighbor(20L, 123, device);
        SdwnNeighbor n1 = new SdwnNeighbor(21L, 212, device);
        Set<SdwnNeighbor> neighbors = new HashSet<>();
        neighbors.add(n0);
        neighbors.add(n1);

        nodeRepo.persistNeighbors(node,neighbors);

        Iterator<SdwnNeighbor> it = neighbors.iterator();
        while (it.hasNext()){
            SdwnNeighbor n =it.next();
            assertNotNull(n.getId());
        }
    }

    @Test
    public void test_fetchSessionActiveNodes(){

    }
    private void createNetworkWithNodes(){

    }

    private SdwnNodeEntity persistNode(SdwnNodeEntity node){
        nodeRepo.create(node,device.getId());

        return node;
    }
}