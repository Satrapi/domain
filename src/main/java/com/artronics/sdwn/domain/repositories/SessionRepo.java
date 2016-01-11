package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.NetworkSession;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionRepo extends PagingAndSortingRepository<NetworkSession,Long>,SessionCustomRepo
{
}
