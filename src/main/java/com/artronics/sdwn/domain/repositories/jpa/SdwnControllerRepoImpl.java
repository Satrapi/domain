package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.repositories.SdwnControllerCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class SdwnControllerRepoImpl implements SdwnControllerCustomRepo
{
    private final static Logger log = Logger.getLogger(SdwnControllerRepoImpl.class);
    @PersistenceContext
    EntityManager em;

    @Override
    public SdwnControllerEntity findByUrl(String url)
    {
        Query q = em.createQuery("FROM com.artronics.sdwn.domain.entities.SdwnControllerEntity n where " +
                                         "n.url=?1");
        q.setParameter(1,url);

        SdwnControllerEntity singleResult = null;
        try {
            singleResult = (SdwnControllerEntity) q.getSingleResult();
        }catch (NoResultException e) {
            singleResult = null;
        }

        return singleResult;
    }

    @Override
    public DeviceConnectionEntity addSwitchingNet(SdwnControllerEntity ctrl,
                                                  DeviceConnectionEntity deviceConnectionEntity)
    {
        throw new NotImplementedException();
//        SdwnControllerEntity con = em.find(SdwnControllerEntity.class,ctrl.getId());
////        con.addSwitchingNet(deviceConnectionEntity);
//
//        deviceConnectionEntity.setSdwnController(con);
//
//        em.persist(con);
//
//        return deviceConnectionEntity;
    }
}
