package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import org.apache.log4j.Logger;

public class EntityFactory
{
    private final static Logger log = Logger.getLogger(EntityFactory.class);

    public static SdwnControllerEntity createController(String url){
        SdwnControllerEntity ctrl = new SdwnControllerEntity(url);

        return ctrl;
    }

    public static DeviceConnectionEntity createSwitchingNet(String url){
        DeviceConnectionEntity net = new DeviceConnectionEntity(url);

        return net;
    }
}
