package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.NeighborCustomRepo;
import com.artronics.sdwn.domain.repositories.NodeRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NeighborRepoImpl implements NeighborCustomRepo
{
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NodeRepo nodeRepo;

    @Override
    @Transactional
    public SdwnNeighbor persist(SdwnNeighbor neighbor, SdwnNodeEntity node)
    {
        SdwnNodeEntity n = em.find(SdwnNodeEntity.class,node.getId());
        n.getNeighbors().add(neighbor);

//        em.persist(neighbor);
        em.persist(n);

        return neighbor;
    }
}
