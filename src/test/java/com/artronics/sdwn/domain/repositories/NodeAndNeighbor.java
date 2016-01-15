package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class NodeAndNeighbor extends BaseRepoTest
{
    @Test
    @Transactional
    @Ignore
    public void node_should_have_neighbors(){
        SdwnNodeEntity node = nodeRepo.findOne(node246.getId());

//        List<SdwnNeighbor> n = node.getNeighbors();
//        assertThat(n.size(),equalTo(2));
//        n.forEach(neighbor -> assertNotNull(neighbor.getNode()));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Ignore
    public void it(){
        SdwnNodeEntity node = new SdwnNodeEntity(246L,device2);
        node.setBattery(23);
        nodeRepo.update(node);

        SdwnNodeEntity persistedNode = nodeRepo.findOne(node246.getId());
        assertThat(persistedNode.getBattery(),equalTo(23));

//        List<SdwnNeighbor> n = persistedNode.getNeighbors();
//        assertThat(n.size(),equalTo(2));
//        n.forEach(neighbor -> assertNotNull(neighbor.getNode()));
    }


}
