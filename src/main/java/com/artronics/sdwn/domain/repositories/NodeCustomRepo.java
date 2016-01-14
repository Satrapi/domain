package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

import java.util.Set;

public interface NodeCustomRepo
{
    SdwnNodeEntity persist(SdwnNodeEntity node);

    SdwnNodeEntity update(SdwnNodeEntity node);

    SdwnNodeEntity create(SdwnNodeEntity node,Long deviceId);

    Set<SdwnNodeEntity> fetchSessionActiveNodes(NetworkSession session);

    Set<SdwnNeighbor> persistNeighbors(SdwnNodeEntity node, Set<SdwnNeighbor> neighbors);

}
