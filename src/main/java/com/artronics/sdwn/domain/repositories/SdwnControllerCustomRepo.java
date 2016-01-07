package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SdwnControllerEntity;

public interface SdwnControllerCustomRepo
{
    SdwnControllerEntity findByUrl(String url);
}
