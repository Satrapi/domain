package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.repositories.SdwnControllerRepo;
import com.artronics.sdwn.domain.repositories.SessionCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class SessionRepoImpl implements SessionCustomRepo
{
    private final static Logger log = Logger.getLogger(SessionRepoImpl.class);

    @Autowired
    private EntityManager em;

    @Autowired
    private SdwnControllerRepo controllerRepo;

    @Override
    @Transactional
    public void expire()
    {
        Query q = em.createQuery("from com.artronics.sdwn.domain.entities.NetworkSession s  where " +
                                         "s.status = ?1");

        q.setParameter(1, NetworkSession.Status.ACTIVE);

        NetworkSession s =(NetworkSession) q.getSingleResult();
        s.setStatus(NetworkSession.Status.EXPIRED);

        em.persist(s);
    }
}
