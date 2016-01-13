package com.artronics.sdwn.domain.repositories;

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
    @Transactional
    public void test_create(){
        SdwnNodeEntity node = new SdwnNodeEntity(34L);

        nodeRepo.create(node,device.getId());

        assertNotNull(node.getId());
    }
}