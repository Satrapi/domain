package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.DeviceConnectionRepo;
import com.artronics.sdwn.domain.repositories.NodeCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
public class NodeRepoImpl implements NodeCustomRepo
{
    private final static Logger log = Logger.getLogger(NodeRepoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DeviceConnectionRepo deviceRepo;

    @Autowired
    private NetworkSession session;

    @Override
    @Transactional
    public SdwnNodeEntity create(SdwnNodeEntity node, Long deviceId)
    {
        DeviceConnectionEntity device = deviceRepo.findOne(deviceId);

        node.setDevice(device);
        node.setSession(session);

        em.persist(node);

        return node;
    }

    @Override
    public Set<Neighbor> persistNeighbors(SdwnNodeEntity node,Set<Neighbor> neighbors)
    {
        SdwnNodeEntity persistedNode =em.find(SdwnNodeEntity.class,node.getId());

        persistedNode.setNeighbors(neighbors);

        return neighbors;
    }
}
