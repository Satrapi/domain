package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SwitchingNetwork;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SwitchingNetRepo extends
                                  PagingAndSortingRepository<SwitchingNetwork,Long>,SwitchingNetCustomRepo
{
}
