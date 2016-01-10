package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NodeRepo extends PagingAndSortingRepository<SdwnNodeEntity,Long>
{
}
