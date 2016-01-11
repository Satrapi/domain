package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.NeighborCustomRepo;
import com.artronics.sdwn.domain.repositories.NodeRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        SdwnNodeEntity n = nodeRepo.findOne(node.getId());

        return n.getNeighbors();
    }

    @Override
    @Transactional
    public SdwnNodeEntity persistNeighborSet(SdwnNodeEntity srcNode, Set<Neighbor> neighbors)
    {
        SdwnNodeEntity n = nodeRepo.findOne(srcNode.getId());

        n.setNeighbors(neighbors);

//        em.persist(n);
        nodeRepo.save(n);

        return n;
    }
}
