package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

import java.util.List;

public interface NeighborCustomRepo
{
    SdwnNeighbor persist(SdwnNeighbor neighbor);

    List<SdwnNeighbor> getNeighbors(SdwnNodeEntity srcNode);
}
