package com.artronics.sdwn.domain.entities.packet;

import java.util.List;

public interface PacketFactory
{
    Packet create(List<Integer> content);
}
