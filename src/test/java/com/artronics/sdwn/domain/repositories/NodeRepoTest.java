package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NodeRepoTest extends BaseRepoTest
{
    @Override
    @Before
    public void setUp() throws Exception
    {
        super.setUp();

    }

    @Test
    public void it(){
//        SdwnNodeEntity node135_2=node135.
        nodeRepo.save(node135);

        List<SdwnNodeEntity> actNode = new ArrayList<>();
        Iterable<SdwnNodeEntity> nodes =nodeRepo.findAll();
        for (SdwnNodeEntity node : nodes) {
            if (node.getAddress().equals(135L))
                actNode.add(node);
        }

        assertThat(actNode.size(),equalTo(2));
    }

    @Test
    public void it_should_add_neighbor(){
        SdwnNodeEntity node141= new SdwnNodeEntity(141L,device);
        SdwnNeighbor n0_141 = new SdwnNeighbor(node141, 205, 50D);
        node0.getNeighbors().add(n0_141);
        nodeRepo.save(node0);


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
    public void test_fetchSessionActiveNodes(){

    }
    private void createNetworkWithNodes(){

    }

    private SdwnNodeEntity persistNode(SdwnNodeEntity node){
        nodeRepo.create(node,device.getId());

        return node;
    }
}