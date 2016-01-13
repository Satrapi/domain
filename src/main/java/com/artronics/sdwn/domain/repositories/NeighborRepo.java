package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import org.apache.log4j.Logger;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NeighborRepo extends PagingAndSortingRepository<SdwnNeighbor,Long>,NeighborCustomRepo
{
}
