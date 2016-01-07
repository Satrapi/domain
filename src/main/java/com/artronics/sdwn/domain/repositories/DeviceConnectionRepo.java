package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceConnectionRepo extends
                                      PagingAndSortingRepository<DeviceConnectionEntity,Long>, DeviceConnectionCustomRepo
{
}
