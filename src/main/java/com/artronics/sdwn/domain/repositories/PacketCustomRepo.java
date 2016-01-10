package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;

public interface PacketCustomRepo
{
    PacketEntity create(PacketEntity packet, Long deviceId);
}
