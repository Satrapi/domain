package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

public interface NeighborCustomRepo
{
    SdwnNeighbor persist(SdwnNeighbor neighbor, SdwnNodeEntity node);
}
