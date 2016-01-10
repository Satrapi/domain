package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;

public interface NodeCustomRepo
{
    SdwnNodeEntity create(SdwnNodeEntity node,Long deviceId);
}
