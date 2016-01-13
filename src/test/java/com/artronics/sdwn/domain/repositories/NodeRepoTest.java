package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

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
    public void test_fetchSessionActiveNodes(){

    }
    private void createNetworkWithNodes(){

    }

    private SdwnNodeEntity persistNode(SdwnNodeEntity node){
        nodeRepo.create(node,device.getId());

        return node;
    }
}