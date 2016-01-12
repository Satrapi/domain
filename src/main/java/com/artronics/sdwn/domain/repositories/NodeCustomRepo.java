package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

import java.util.Set;

public interface NodeCustomRepo
{
    SdwnNodeEntity create(SdwnNodeEntity node,Long deviceId);

    Set<SdwnNodeEntity> fetchSessionActiveNodes(NetworkSession session);

    Set<Neighbor> persistNeighbors(SdwnNodeEntity node,Set<Neighbor> neighbors);
}
