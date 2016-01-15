package com.artronics.sdwn.domain.repositories.jpa;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.DeviceConnectionRepo;
import com.artronics.sdwn.domain.repositories.NodeCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;

@Repository
public class NodeRepoImpl implements NodeCustomRepo
{
    private final static Logger log = Logger.getLogger(NodeRepoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NetworkSession session;

    @Autowired
    private DeviceConnectionRepo deviceRepo;

    @Override
    @Transactional
    public SdwnNodeEntity persist(SdwnNodeEntity node)
    {
        node.setSession(session);
        em.persist(node);

        return node;
    }

    @Override
    @Transactional
    public SdwnNodeEntity merge(SdwnNodeEntity node)
    {
        node.setSession(session);
        em.merge(node);

        return node;
    }

    @Override
    public SdwnNodeEntity update(SdwnNodeEntity node)
    {
        Query q = em.createQuery("select n from SdwnNodeEntity n where " +
                "n.session = ?1 AND "+
                                         "n.address = ?2 AND " +
                                         "n.device = ?3 " +
                                         "order by n.id desc ",SdwnNodeEntity.class);

        q.setParameter(1,session);
        q.setParameter(2,node.getAddress());
        q.setParameter(3,node.getDevice());

        SdwnNodeEntity perNode = (SdwnNodeEntity) q.getSingleResult();

        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public SdwnNodeEntity create(SdwnNodeEntity node, Long deviceId)
    {
        DeviceConnectionEntity device = deviceRepo.findOne(deviceId);

        node.setDevice(device);

        em.persist(node);

        return node;
    }

    @Override
    public Set<SdwnNodeEntity> fetchSessionActiveNodes(NetworkSession session)
    {
        return null;
    }

    @Override
    public Set<SdwnNeighbor> persistNeighbors(SdwnNodeEntity node, Set<SdwnNeighbor> neighbors)
    {
        SdwnNodeEntity persistedNode =em.find(SdwnNodeEntity.class,node.getId());

        throw new NotImplementedException();
    }
}
