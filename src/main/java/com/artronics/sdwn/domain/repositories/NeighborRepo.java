package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.Neighbor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NeighborRepo extends PagingAndSortingRepository<Neighbor,Long>,NeighborCustomRepo
{
}
