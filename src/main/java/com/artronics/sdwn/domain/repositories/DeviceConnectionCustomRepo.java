package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;

public interface DeviceConnectionCustomRepo
{
    DeviceConnectionEntity findByUrl(String url);

    DeviceConnectionEntity create(DeviceConnectionEntity device, Long controllerId);
}
