package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.SwitchingNetwork;

public interface SdwnControllerCustomRepo
{
    SdwnControllerEntity findByUrl(String url);

    SwitchingNetwork addSwitchingNet(SdwnControllerEntity ctrl,SwitchingNetwork switchingNetwork);
}
