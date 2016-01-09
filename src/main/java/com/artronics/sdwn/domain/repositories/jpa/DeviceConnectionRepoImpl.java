package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.repositories.DeviceConnectionCustomRepo;
import com.artronics.sdwn.domain.repositories.SdwnControllerRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DeviceConnectionRepoImpl implements DeviceConnectionCustomRepo
{
    private final static Logger log = Logger.getLogger(DeviceConnectionRepoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SdwnControllerRepo controllerRepo;

    @Override
    public DeviceConnectionEntity findByUrl(String url)
    {
        Query q = em.createQuery("FROM com.artronics.sdwn.domain.entities.DeviceConnectionEntity n where " + "n.url=?1");

        q.setParameter(1,url);

        DeviceConnectionEntity singleResult = null;
        try {
            singleResult = (DeviceConnectionEntity) q.getSingleResult();
        }catch (NoResultException e) {
            singleResult = null;
        }

        return singleResult;
    }

    @Override
    @Transactional
    public DeviceConnectionEntity create(DeviceConnectionEntity device, Long controllerId)
    {
        SdwnControllerEntity ctrl = controllerRepo.findOne(controllerId);

        device.setSdwnController(ctrl);
        em.persist(device);

        return device;
    }
}
