package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.SwitchingNetwork;
import com.artronics.sdwn.domain.repositories.SdwnControllerRepo;
import com.artronics.sdwn.domain.repositories.SwitchingNetCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class SwitchingNetRepoImpl implements SwitchingNetCustomRepo
{
    private final static Logger log = Logger.getLogger(SwitchingNetRepoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SdwnControllerRepo controllerRepo;

    @Override
    public SwitchingNetwork findByUrl(String url)
    {
        Query q = em.createQuery("FROM com.artronics.sdwn.domain.entities.SwitchingNetwork n where " +
                                         "n.url=?1");
        q.setParameter(1,url);

        SwitchingNetwork singleResult = null;
        try {
            singleResult = (SwitchingNetwork) q.getSingleResult();
        }catch (NoResultException e) {
            singleResult = null;
        }

        return singleResult;
    }

    @Override
    public SwitchingNetwork create(SwitchingNetwork network, Long controllerId)
    {
        SdwnControllerEntity ctrl = controllerRepo.findOne(controllerId);

        network.setSdwnController(ctrl);
        em.persist(network);

        return network;
    }
}
