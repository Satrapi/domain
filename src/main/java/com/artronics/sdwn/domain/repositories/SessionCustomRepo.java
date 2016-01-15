package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.NetworkSession;

public interface SessionCustomRepo
{
    NetworkSession findActiveSession();
    void expire();
}
