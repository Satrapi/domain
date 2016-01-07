package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PacketRepo extends PagingAndSortingRepository<PacketEntity,Long>
{
}
