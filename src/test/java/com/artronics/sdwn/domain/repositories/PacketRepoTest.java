package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.helpers.FakePacketFactory;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

public class PacketRepoTest extends BaseRepoTest
{
    private FakePacketFactory factory = new FakePacketFactory();

    @Test
    @Transactional
    public void it_should_save_a_packet(){
        PacketEntity packet = PacketEntity.create(factory.createRawDataPacket(),device);

        packetRepo.save(packet);

        assertNotNull(packet.getId());
    }

    @Test
    @Transactional
    public void test_create(){
        PacketEntity packet = PacketEntity.create(factory.createRawDataPacket(),device);

        packetRepo.create(packet,device.getId());

        assertNotNull(packet.getId());
    }
}