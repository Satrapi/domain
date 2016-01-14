package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;

public interface DeviceConnectionCustomRepo
{
    DeviceConnectionEntity findByUrl(String url);

    DeviceConnectionEntity create(DeviceConnectionEntity device,
                                  SdwnControllerEntity controller);
}
