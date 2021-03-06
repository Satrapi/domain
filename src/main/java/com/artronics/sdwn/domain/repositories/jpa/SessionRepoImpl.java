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
import java.util.List;

@Repository
public class SessionRepoImpl implements SessionCustomRepo
{
    private final static Logger log = Logger.getLogger(SessionRepoImpl.class);

    @Autowired
    private EntityManager em;

    @Autowired
    private SdwnControllerRepo controllerRepo;

    @Override
    public NetworkSession findActiveSession()
    {
        List<NetworkSession> s = findSession(NetworkSession.Status.ACTIVE);

        if (s.isEmpty())
            return null;

        return s.get(0);
    }

    @Override
    @Transactional
    public void expire()
    {
        List<NetworkSession> s = findSession(NetworkSession.Status.ACTIVE);

        s.forEach(session -> {
            session.setStatus(NetworkSession.Status.EXPIRED);
            em.persist(session);
        });
    }

    private List<NetworkSession> findSession(NetworkSession.Status status){
        Query q = em.createQuery("select s from NetworkSession s  where " +
                                         "s.status = ?1 " +
                                         "order by s.id DESC ");

        q.setParameter(1, status);

        List<NetworkSession> s =(List<NetworkSession>) q.getResultList();

        return s;
    }

}
