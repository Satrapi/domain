package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SwitchingNetwork;

public interface SwitchingNetCustomRepo
{
    SwitchingNetwork findByUrl(String url);

    SwitchingNetwork create(SwitchingNetwork network,Long controllerId);
}
