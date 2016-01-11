package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

import java.util.Set;

public interface NeighborCustomRepo
{
    Set<Neighbor> fetchNeighbors(SdwnNodeEntity node);

    SdwnNodeEntity persistNeighborSet(SdwnNodeEntity srcNode, Set<Neighbor> neighbors);
}
