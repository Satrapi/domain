package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.NeighborCustomRepo;
import com.artronics.sdwn.domain.repositories.NodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class NeighborRepoImpl implements NeighborCustomRepo
{
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NodeRepo nodeRepo;

    @Override
    @Transactional
    public SdwnNeighbor persist(SdwnNeighbor neighbor)
    {
        em.persist(neighbor);

        return neighbor;
    }

    @Override
    public List<SdwnNeighbor> getNeighbors(SdwnNodeEntity srcNode)
    {
        Query q = em.createQuery("select n from SdwnNeighbor n " +
                                         "where n.node =?1", SdwnNeighbor.class);
        q.setParameter(1,srcNode);

        return (List<SdwnNeighbor>) q.getResultList();
    }

}
