package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.repositories.DeviceConnectionRepo;
import com.artronics.sdwn.domain.repositories.PacketCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PacketRepoImpl implements PacketCustomRepo
{
    private final static Logger log = Logger.getLogger(PacketRepoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NetworkSession session;

    @Autowired
    private DeviceConnectionRepo deviceRepo;


    @Override
    public PacketEntity persist(PacketEntity packet)
    {
        packet.setSession(session);
        em.persist(packet);

        return packet;
    }

    @Override
    @Transactional
    public PacketEntity create(PacketEntity packet, Long deviceId)
    {
        DeviceConnectionEntity device = deviceRepo.findOne(deviceId);

        packet.setDevice(device);

        em.persist(packet);

        return packet;
    }
}
