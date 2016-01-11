package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.NeighborCustomRepo;
import com.artronics.sdwn.domain.repositories.NodeRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;

@Repository
public class NeighborRepoImpl implements NeighborCustomRepo
{
    private final static Logger log = Logger.getLogger(NeighborRepoImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NodeRepo nodeRepo;

    @Override
    public Set<Neighbor> fetchNeighbors(SdwnNodeEntity node)
    {
        Query q = em.createQuery("FROM com.artronics.sdwn.domain.entities.node.SdwnNodeEntity n where " +
                                         "n.id = ?1 AND n.status = 'ACTIVE'");

        q.setParameter(1,node.getId());
        SdwnNodeEntity n =(SdwnNodeEntity) q.getSingleResult();

        return n.getNeighbors();
    }
}
