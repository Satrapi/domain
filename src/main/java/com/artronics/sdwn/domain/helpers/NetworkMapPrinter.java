package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.node.Node;
import com.artronics.sdwn.domain.map.NetworkMap;

public interface NetworkMapPrinter<N extends Node>
{
    String printNetworkMap(NetworkMap<N> map);

    String printNetworkMap(NetworkMap<N> map, DeviceConnectionEntity device);
}
