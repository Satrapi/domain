package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

import java.util.Set;

public interface NeighborCustomRepo
{
    SdwnNeighbor persist(SdwnNeighbor neighbor, SdwnNodeEntity node);

    Set<SdwnNeighbor> getNeighbors(SdwnNodeEntity srcNode);
}
