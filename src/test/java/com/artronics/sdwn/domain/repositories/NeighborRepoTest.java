package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class NeighborRepoTest extends BaseRepoTest
{
    private SdwnNodeEntity node;

    private SdwnNodeEntity n1;
    private SdwnNodeEntity n2;

    private SdwnNeighbor ne1;
    private SdwnNeighbor ne2;

    @Override
    @Before
    @Transactional
    public void setUp() throws Exception
    {
        super.setUp();
        node = new SdwnNodeEntity(123L,device);

        n1 = new SdwnNodeEntity(11L,device);
        n2 = new SdwnNodeEntity(12L,device);

        ne1 = new SdwnNeighbor(node,100,155D);
        ne2 = new SdwnNeighbor(node,200,55D);

        nodeRepo.persist(node);
        nodeRepo.persist(n1);
        nodeRepo.persist(n2);
    }

    @Test
    @Transactional
    @Ignore
    public void it_should_persist_neighbor(){
        persistNeighbors();

//        assertNotNull(ne1.getId());
    }

    @Test
    @Transactional
    @Ignore
    public void it_should_getNeighbors(){
        persistNeighbors();

//        List<SdwnNeighbor> neighbors = neighborRepo.getNeighbors(node);
//        assertThat(neighbors.size(),equalTo(2));
    }

    @Ignore
    @Test
    @Transactional
    public void it_should_EAGERly_load_node(){
        persistNeighbors();

//        List<SdwnNeighbor> neighbors = neighborRepo.getNeighbors(node);

//        assertNotNull(neighbors.get(0).getNode().getId());
    }

    private void persistNeighbors(){
//        neighborRepo.persist(ne1);
//        neighborRepo.persist(ne2);
    }
}