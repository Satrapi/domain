package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

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
    public void it_should_persist_a_node(){
        SdwnNodeEntity node = new SdwnNodeEntity(124L,device);
        nodeRepo.persist(node);

        assertNotNull(node.getId());
    }

    @Test
    @Transactional
    public void it_should_update_node(){
        nodeRepo.deleteAll();
        SdwnNodeEntity node = new SdwnNodeEntity(124L,device);
        nodeRepo.persist(node);

        node.setBattery(87);
        nodeRepo.persist(node);

        Iterable<SdwnNodeEntity> it =nodeRepo.findAll();
        Integer i=new Integer(0);
        it.forEach((n)->{
        if (n.equals(node))
                assertThat(n.getBattery(),equalTo(87));
        });
        assertThat(nodeRepo.count(),equalTo(1L));

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