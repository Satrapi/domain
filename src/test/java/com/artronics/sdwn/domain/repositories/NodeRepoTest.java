package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Ignore;
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
    public void it_should_persist_a_node(){
        SdwnNodeEntity node = new SdwnNodeEntity(124L,device);
        nodeRepo.persist(node);

        assertNotNull(node.getId());
    }

    @Ignore
    @Test
    @Transactional
    public void it_should_update_node(){
        SdwnNodeEntity node = new SdwnNodeEntity(124L,device);
        nodeRepo.persist(node);

        nodeRepo.persist(node);

    }

}